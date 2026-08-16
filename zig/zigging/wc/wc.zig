const std = @import("std");
const debugPrint = std.debug.print;

// this doesn't do anything. it's just a reference
const Flag:type = enum {
    bytes,
    chars,
    lines,
    lineMaxWidth,
    words,
};

//todo: process the configs into intructions in a way that isn't retarded.
const Config:type = struct {
    help:bool  = false,
    lines:bool = false,
    words:bool = false,
    chars:bool = false,
    bytes:bool = false,
};

pub fn main(init: std.process.Init) !void {
    const io = init.io;
    const allocator = init.arena.allocator();
    var stdout_writer = std.Io.File.stdout().writer(io, &.{});
    var stdout = &stdout_writer.interface;
    var config:Config = Config{};
    var header = true;

    var args = try init.minimal.args.toSlice(allocator);
    args = args[1..];

    if (args.len < 1) {
        debugPrint("No filepath provided\n", .{});
        return;
    }

    const filenames = try readArgs(allocator, args, &config);
    if (config.help) {
        try printHelp(stdout);
        return;
    }
    if (filenames.len == 0) {
        debugPrint("No filename provided\n", .{});
        return error.NoFilename;
    }

    var totalNewlines:usize = 0;
    var totalWords:usize    = 0;
    var totalBytes:usize    = 0;
    var totalChars:usize    = 0;

    var max_newlines: usize = "newlines".len;
    var max_words:    usize = "words".len;
    var max_chars:    usize = "chars".len;
    var max_bytes:    usize = "bytes".len;
    var max_filename: usize = "filename".len;

    for (filenames) |filename| {
        var file = try std.Io.Dir.cwd().openFile(io, filename, .{});
        defer file.close(io);
        const stat = try file.stat(io);
        if (stat.size == 0) return error.emptyFile;

        const buffer = try allocator.alloc(u8, stat.size);
        defer allocator.free(buffer);

        // this line writes the contents of the file to the buffer although i'm not sure how.
        // i copied it off reddit OMEGALUL
        _ = try file.readPositionalAll(io, buffer, 0);

        const newlines:usize = countNewline(buffer);
        const words:usize    = countWord(buffer);
        const chars:usize    = countChars(buffer);
        const bytes:usize    = stat.size;

        max_newlines         = @max(max_newlines, digitCount(newlines));
        max_words            = @max(max_words, digitCount(words));
        max_chars            = @max(max_chars, digitCount(chars));
        max_bytes            = @max(max_bytes, digitCount(bytes));
        max_filename         = @max(max_filename, filename.len);
        
        if (header) {
            try printHeader(stdout, &config);
            header = false;
        }

        var flagged:bool = false;
        if (config.lines) {
            try printPadded(stdout, newlines, max_newlines);
            try stdout.print(" | ", .{});
            flagged = true;
        }
        if (config.words) {
            try printPadded(stdout, words, max_words);
            try stdout.print(" | ", .{});
            flagged = true;
        }
        if (config.chars) {
            try printPadded(stdout, chars, max_chars);
            try stdout.print(" | ", .{});
            flagged = true;
        }
        if (config.bytes) {
            try printPadded(stdout, bytes, max_bytes);
            try stdout.print(" | ", .{});
            flagged = true;
        }
        if (!flagged) {
            try printPadded(stdout, newlines, max_newlines);
            try stdout.print(" | ", .{});
            try printPadded(stdout, words, max_words);
            try stdout.print(" | ", .{});
            try printPadded(stdout, bytes, max_bytes);
            try stdout.print(" | ", .{});
        }
        try stdout.print("{s}\n", .{filename});

        totalNewlines +=newlines;
        totalWords    +=words;
        totalBytes    +=bytes;
        totalChars    +=buffer.len;
    }

    if (filenames.len > 1) {
        var flagged:bool = false;
        if (config.lines) {
            try printPadded(stdout, totalNewlines, max_newlines);
            try stdout.print(" | ", .{});
            flagged = true;
        }
        if (config.words) {
            try printPadded(stdout, totalWords, max_words);
            try stdout.print(" | ", .{});
            flagged = true;
        }
        if (config.chars) {
            try printPadded(stdout, totalChars, max_chars);
            try stdout.print(" | ", .{});
            flagged = true;
        }
        if (config.bytes) {
            try printPadded(stdout, totalBytes, max_bytes);
            try stdout.print(" | ", .{});
            flagged = true;
        }
        if (!flagged) {
            try printPadded(stdout, totalNewlines, max_newlines);
            try stdout.print(" | ", .{});
            try printPadded(stdout, totalWords, max_words);
            try stdout.print(" | ", .{});
            try printPadded(stdout, totalBytes, max_bytes);
            try stdout.print(" | ", .{});
        }
        try stdout.print("{s}\n", .{"total"});
        
    }
}

fn printHeader(writer:anytype, config:*Config) !void {
    var flagged:bool = false;
    if (config.lines) {
        try writer.print("newlines | ", .{});
        flagged = true;
    }
    if (config.words) {
        try writer.print("words | ", .{});
        flagged = true;
    }
    if (config.chars) {
        try writer.print("chars | ", .{});
        flagged = true;
    }
    if (config.bytes) {
        try writer.print("bytes | ", .{});
        flagged = true;
    }
    if (!flagged) {
        try writer.print("newlines | words | bytes | ", .{});
    }
    try writer.print("filename\n", .{});
}

fn printPadded(writer: anytype, value: usize, width: usize) !void {
    const digits = digitCount(value);
    var i: usize = digits;
    while (i < width) : (i += 1) {
        try writer.print(" ", .{});
    }
    try writer.print("{d}", .{value});
}

fn printHelp(writer:anytype) !void {
    try writer.print(
        \\Usage: wc [OPTION]... [FILE]...
            \\Print newline, word, and byte counts for each FILE, and a total line if
            \\more than one FILE is specified.  A word is a non-zero-length sequence of
            \\printable characters delimited by white space.
            \\
            \\The options below may be used to select which counts are printed, always in
            \\the following order: newline, word, character, byte.
            \\  -c,        print the byte counts
            \\  -m,        print the character counts
            \\  -l,        print the newline counts
            \\  -w,        print the word counts
            \\             display this help and exit
            \\
            \\This is a program i wrote in zig to help learn both zig and low level programming.
            \\it is an attempt to replicate the standard wc binary from GNU coreutils.
            , .{});
    try writer.print("\n", .{});
}

fn digitCount(n: usize) usize {
    var x = n;
    var count: usize = 1;
    while (x >= 10) : (x /= 10) {
        count += 1;
    }
    return count;
}

fn readArgs(allocator: std.mem.Allocator, args:[]const [:0]const u8, config: *Config) ![]const [:0]const u8 {
    var filenames = std.ArrayList([:0]const u8).empty;
    for (args) |arg| {
        if (arg.len > 0 and arg[0] == '-') {
            try parseFlags(arg, config);
        } else {
            try filenames.append(allocator, arg);
        }
    }
    return try filenames.toOwnedSlice(allocator);
}

fn parseFlags(arg:[:0]const u8, config: *Config) !void {
    if (arg.len == 0 or arg[0] != '-') return;
    for (arg[1..]) |char| {
        switch(char) {
            'h' => config.help  = true,
            'l' => config.lines = true,
            'w' => config.words = true,
            'm' => config.chars = true,
            'c' => config.bytes = true,
            else => {
                debugPrint("Invalid flag: -{c}\n", .{char});
                debugPrint("Use 'wc -h' for help\n", .{});
                return error.InvalidFlag;
            }
        }
    }
}

fn countWord(buffer:[]u8) usize {
    var i:usize = 0;
    var count:usize = 0;
    var word:bool = false;
    while (i < buffer.len) : (i += 1) {
        if (buffer[i] == ' ' or buffer[i] == '\n' or buffer[i] == '\t') {
            word = false;
        } else {
            if (!word) {
            count += 1;
            word = true;
            }
        }
    }
    return count;
}

fn countNewline(buffer:[]u8) usize {
    var i: usize = 0;
    var count: usize = 0;
    while (i < buffer.len) : (i += 1) {
        if (buffer[i] == '\n') {
            count += 1;
        }
    }
    return count;
}

fn countChars(buffer:[]u8) usize {
    var i:usize = 0;
    while (i < buffer.len) {
        i += 1;
    }
    return i;
}

const std = @import("std");

pub fn main() void {
    const text = "i love you\n";
    _ = std.os.linux.write(1, text, text.len);
}

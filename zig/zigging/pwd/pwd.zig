const std = @import("std");

pub fn main() void {
    var buf: [4096]u8 = undefined;
    _ = std.os.linux.getcwd(&buf, buf.len);

    _ = std.os.linux.write(1, &buf, buf.len);
    std.debug.print("\n", .{});
}

package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * ClassName:_71
 * Package:stack
 * Description:
 * <p>
 * 📌 题目内容
 * 给定一个 Unix 风格的绝对路径（以 '/' 开头），将其转换为 “规范路径”。
 * <p>
 * 规范路径需满足：
 * <p>
 * 始终以 '/' 开头
 * 任意两个目录名之间只有一个 '/'
 * 路径不以 '/' 结尾（除非是根目录）
 * 路径中不含 .（当前目录）或 ..（上一级目录）
 * 空路径视为根目录 "/"
 * 示例：
 * <p>
 * 输入："/home/" → 输出："/home"
 * 输入："/../" → 输出："/"
 * 输入："/home//foo/" → 输出："/home/foo"
 * 输入："/a/./b/../../c/" → 输出："/c"
 * 🏷️ 题目标签
 * 栈 | 字符串 | 中等
 * <p>
 * 🔗 题目链接
 * <a href="https://leetcode.cn/problems/simplify-path">...</a>
 */
public class _71 {
    /**
     * 典型的栈运用题目
     * 目录名以'/'作为分割,然后以此内容进行循环
     * 简化涉及到多种情况的处理,需要仔细应对
     * 1. 当前目录'.'和空串直接跳过
     * 2. 上一级目录'..'需要判断栈是否为空,不为空则出栈
     * 3. 其他情况入栈
     * 处理完各种情况的处理后就开始构建结果了
     * 1. 栈为空,则返回根目录'/'
     * 2. 栈不为空,则依次出栈,并添加到结果中,注意添加'/'
     * 3. 最后返回结果即可
     * 🔑 解题思路
     */
    public String simplifyPath_1(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] dirs = path.split("/");
        for (String dir : dirs) {
            if (dir.equals(".") || dir.isEmpty()) {
                continue;
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        return sb.toString();
    }

    /**
     * 使用ArrayList模拟栈
     * 🔑 解题思路
     */
    public String simplifyPath_2(String path) {
        String[] parts = path.split("/");
        ArrayList<String> stack = new ArrayList<>();
        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            } else if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {
                stack.add(part);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (String part : stack) {
            sb.append("/").append(part);
        }
        return sb.toString();
    }
    /**
     * 注意点:
     * 1. 强调 split("/") 的行为：
     *  "/a//b" → ["", "a", "", "b"]
     *  开头和结尾的 / 会产生空字符串
     * 2. 解释为什么用栈：
     *  ".." 表示“撤销”上一个操作 → LIFO 天然匹配
     * 3. 对比其他方法：
     *  正则替换？→ 难以处理嵌套的 ..
     *  递归？→ 不必要，栈更直观
     * 4. 可扩展讨论：
     *  相对路径如何处理？将相对路径转为绝对路径后再简化,需要进行一定程度的拼接
     *  Windows 路径（\）？→ 需预处理
     */
}

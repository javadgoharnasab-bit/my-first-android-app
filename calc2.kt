fun main() {
    println("=== ماشین حساب ===")
    println("عدد اول را وارد کنید:")
    val a = readln().toDouble()

    println("عملیات را انتخاب کنید (+, -, *, /):")
    val op = readln()

    println("عدد دوم را وارد کنید:")
    val b = readln().toDouble()

    val result = when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if (b == 0.0) {
            println("خطا: تقسیم بر صفر ممکن نیست!")
            return
        } else {
            a / b
        }
        else -> {
            println("عملیات نامعتبر!")
            return
        }
    }

    println("نتیجه: $result")
}

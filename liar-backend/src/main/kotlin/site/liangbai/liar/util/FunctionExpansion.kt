package site.liangbai.liar.util

fun String.ifNotEmpty(block: (String) -> Unit) {
    if (this.isNotEmpty()) {
        block(this)
    }
}

fun Int.ifNotZero(block: (Int) -> Unit) {
    if (this != 0) {
        block(this)
    }
}
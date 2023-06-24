package com.example.review.app.exception


object ExceptionMessage {

    const val REVIEW_SAVE_PRODUCT_ID = "Review 엔티티 에서 product id 는 0 보다 커야 한다"

    const val REVIEW_NOT_SAVE = "Review 엔티티가 정상적으로 저장이 되지 않았습니다"

    const val REVIEW_SELECT_NOT_FOUND = "조회된 Review 엔티티를 찾을 수 없습니다"

    const val ID_NOT_NUll = "args 의 id 값은 null을 허용하지 않습니다"

    const val ARGS_SEARCH_COMMAND_NOT_NULL = "ReviewSearchCommand is product_id and author is not null"
}

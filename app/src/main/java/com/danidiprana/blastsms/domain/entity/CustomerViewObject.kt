package com.danidiprana.blastsms.domain.entity

data class CustomerViewObject(
  val name: String = "",
  val phone: String = "",
  var isSelected: Boolean = false
)
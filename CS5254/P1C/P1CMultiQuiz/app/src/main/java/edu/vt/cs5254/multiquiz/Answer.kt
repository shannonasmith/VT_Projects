package edu.vt.cs5254.multiquiz

import androidx.annotation.StringRes

data class Answer(@StringRes // looks like a class, but its a constructor
                  val textResId: Int,
                  val isCorrect: Boolean,
                  var isEnabled: Boolean = true,
                  var isSelected: Boolean = false,)
package de.animebook.feature.base.extensions

import androidx.recyclerview.widget.DiffUtil

fun <T> diffUtilItemCallback(
    areContentsTheSame: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem -> oldItem == newItem },
    areItemsTheSame: (oldItem: T, newItem: T) -> Boolean
) = object : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) =
        areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        areContentsTheSame(oldItem, newItem)
}
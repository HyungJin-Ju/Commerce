package com.example.domain.usecase

import androidx.paging.PagingSource
import com.example.domain.model.Section
import com.example.domain.repository.SectionRepository
import javax.inject.Inject

class GetSectionsUseCase @Inject constructor(
    private val repository: SectionRepository
) {
    operator fun invoke(): PagingSource<Int, Section> = repository.getSections()
}
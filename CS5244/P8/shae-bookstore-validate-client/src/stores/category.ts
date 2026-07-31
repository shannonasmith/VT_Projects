import {defineStore} from 'pinia'
import type {CategoryItem} from '@/types'
import {apiUrl} from '@/api'
const DEFAULT_CATEGORY_NAME = "Staff Picks";
export const useCategoryStore = defineStore("category", {
    state: () => ({
        categoryList: [] as CategoryItem[],
        selectedCategoryName: DEFAULT_CATEGORY_NAME as string,
    }),
    actions: {
        async fetchCategories() {
            const response = await fetch(`${apiUrl}/categories/`)
            const data = await response.json();
            this.categoryList = data as CategoryItem[];
        },
        async setSelectedCategoryName(selectedCategoryName: string) {
            this.selectedCategoryName = selectedCategoryName;
        },
    },
    getters: {
        categoryName(): string {
            return this.selectedCategoryName || this.defaultCategoryName;
        },

        defaultCategoryName(): string {
            return DEFAULT_CATEGORY_NAME;
        },
    },
});
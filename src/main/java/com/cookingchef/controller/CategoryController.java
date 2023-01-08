package com.cookingchef.controller;

import com.cookingchef.facade.CategoryFacade;

import java.sql.SQLException;

public class CategoryController {
    private CategoryFacade categoryFacade;

    public CategoryController() {
        this.categoryFacade = CategoryFacade.getCategoryFacade();
    }


    public void onClickButtonCreateCategory() {
        if (this.nameCategory == null) {
            this.showText.setText("Please enter a name for the category");
        } else {
            try {
                this.categoryFacade.createCategory(nameCategory);
                showText.setText("Category created");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onClickButtonDeleteCategory() {
        try {
            this.categoryFacade.deleteCategory(idCategory);
            showText.setText("Category deleted");
        } catch (SQLException e) {
            // TODO : gérer l'exception
            showText.setText("Category not deleted");
            throw new RuntimeException(e);
        }
    }

    public void onClickButtonUpdateCategory() {
        if (this.nameCategory == null) {
            this.showText.setText("Please enter a name for the category");
        } else {
            try {
                this.categoryFacade.updateCategory(nameCategory);
                showText.setText("Category updated");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

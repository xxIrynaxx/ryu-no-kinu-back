package com.example.demo.dto.requestDTO;

import com.example.demo.model.Theme;

public class UpdateUserThemeDTO {
  private Theme theme;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}

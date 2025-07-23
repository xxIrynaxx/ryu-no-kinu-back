package com.example.demo.util;

import java.util.Map;

import com.example.demo.model.Translation;

public class TranslationUtil {
  
  public static Translation getTranslationOrDefault(Map<String, Translation> translations, String lang,
      String defaultLang) {
    if (translations == null)
      return new Translation("", "");
    return translations.getOrDefault(lang, translations.getOrDefault(defaultLang, new Translation("", "")));
  }
}

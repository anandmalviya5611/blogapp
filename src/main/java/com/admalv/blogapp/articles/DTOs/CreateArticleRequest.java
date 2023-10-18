package com.admalv.blogapp.articles.DTOs;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {
    @NonNull
    private String title;
    @Nullable
    private String Subtitle;
    @NonNull
    private String body;
    @NonNull
    private String slug;

}

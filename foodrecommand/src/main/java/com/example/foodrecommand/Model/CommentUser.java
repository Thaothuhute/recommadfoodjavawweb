package com.example.foodrecommand.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentUser {
    public String id;
    public Long idUSer;
    public Long idFood;
    public String content;
}

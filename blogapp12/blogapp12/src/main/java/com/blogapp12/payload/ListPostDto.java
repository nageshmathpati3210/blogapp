package com.blogapp12.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ListPostDto
{

    private List<PostDto> postDtoList;

    private int PageNo;

    private int pageSize;


    private boolean firstPage;

    private boolean lastPage;

    private int totalElements;


    private int totalPages;


}

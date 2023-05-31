package com.redditclone.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException  {

    String resourceName;
    String fieldName;
    String fieldValue;

}

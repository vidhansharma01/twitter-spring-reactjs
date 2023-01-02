package com.gmail.merikbest2015.repository.projection;

import com.gmail.merikbest2015.projection.ImageProjection;

public interface ListOwnerProjection {
    Long getId();
    String getFullName();
    String getUsername();
    ImageProjection getAvatar();
}
package com.nftgram.core.domain.common;


import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.common.value.FileType;
import com.nftgram.core.domain.common.value.ImageType;
import com.nftgram.core.domain.common.value.TableType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class ImageStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Enumerated(value =  EnumType.STRING)
    @Column(name = "table_type" , nullable = false , length = 10)
    private TableType tableType;

    @Column(nullable = false)
    private Long parentId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "image_type" , nullable = false , length = 10)
    private ImageType imageType;

    @Column(nullable = false)
    private String realPath;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileSize;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "file_type" , nullable = false , length = 10)
    private FileType fileType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false , length = 10)
    private ActiveStatus activeStatus;
}

package com.nftgram.core.domain.common;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.common.value.FileType;
import com.nftgram.core.domain.common.value.ImageType;
import com.nftgram.core.domain.common.value.TableType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table
public class ImageStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Enumerated(value =  EnumType.STRING)
    @Column(name = "table_type")
    private TableType tableType;

    @Column
    private Long parentId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "image_type")
    private ImageType imageType;

    @Column
    private String realPath;

    @Column
    private String filePath;

    @Column
    private String fileName;

    @Column
    private Long fileSize;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "file_type")
    private FileType fileType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false)
    private ActiveStatus activeStatus;
}

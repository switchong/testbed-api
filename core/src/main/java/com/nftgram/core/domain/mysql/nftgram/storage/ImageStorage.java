package com.nftgram.core.domain.mysql.nftgram.storage;


import com.nftgram.core.domain.mysql.common.value.ActiveStatus;
import com.nftgram.core.domain.mysql.nftgram.storage.value.FileType;
import com.nftgram.core.domain.mysql.nftgram.storage.value.ImageType;
import com.nftgram.core.domain.mysql.nftgram.storage.value.TableType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
public class ImageStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private TableType tableType;

    private Long parenttId;

    private ImageType imageType;

    private String realPath;

    private String filePath;

    private String fileName;

    private String fileSize;

    private FileType fileType;

    private ActiveStatus activeStatus;
}

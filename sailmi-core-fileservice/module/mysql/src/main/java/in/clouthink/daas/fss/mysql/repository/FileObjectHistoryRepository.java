package in.clouthink.daas.fss.mysql.repository;

import in.clouthink.daas.fss.mysql.model.FileObject;
import in.clouthink.daas.fss.mysql.model.FileObjectHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dz
 */
public interface FileObjectHistoryRepository extends AbstractRepository<FileObjectHistory> {

    Page<FileObjectHistory> findByFileObject(FileObject fileObject, Pageable pageable);

    List<FileObjectHistory> findByFileObject(FileObject fileObject);

    List<FileObjectHistory> findByFileObjectId(String fileObjectId);

    void deleteByFileObjectId(String id);

    void deleteByFileObject(FileObject result);
}

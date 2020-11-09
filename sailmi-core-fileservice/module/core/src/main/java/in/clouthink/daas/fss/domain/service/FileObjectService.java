package in.clouthink.daas.fss.domain.service;

import in.clouthink.daas.fss.domain.model.FileObject;
import in.clouthink.daas.fss.domain.model.FileObjectHistory;
import in.clouthink.daas.fss.domain.request.FileObjectSaveRequest;
import in.clouthink.daas.fss.domain.request.FileObjectSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The file object and the associated attributes is stored in the file storage server.
 * To improve the file object(not physical object) access performance,
 * we need the file object service which save the file object attributes on the local database.
 * <p>
 *
 * @author dz
 */
public interface FileObjectService {

    /**
     * @param id the id of file object
     * @return FileObject
     */
    FileObject findById(String id);

    /**
     * @param storedFileName the stored file name which returned by underlying store service
     * @return FileObject
     */
    FileObject findByStoredFilename(String storedFileName);

    /**
     * @param id the id of file object not the id of history id
     * @return the history of the FileObject
     */
    List<FileObjectHistory> findHistoryByFileObjectId(String id);

    /**
     * @param saveRequest the request to store file
     * @return FileObject
     */
    FileObject save(FileObjectSaveRequest saveRequest);

    /**
     * @param fileObject
     * @return
     */
    FileObjectHistory saveAsHistory(FileObject fileObject);

    /**
     * Delete the file object from database (not physically) .
     *
     * @param id the id of file object
     * @return FileObject
     */
    FileObject deleteById(String id);

    /**
     * Delete the file object from database (not physically) .
     *
     * @param storedFileName full stored file name, partial is not supported
     * @return FileObject
     */
    FileObject deleteByStoredFilename(String storedFileName);

    /**
     * @param searchRequest the search request supporting different query condition
     * @return FileObject Page
     */
    Page<FileObject> search(FileObjectSearchRequest searchRequest);

}

package in.clouthink.daas.fss.zimg.impl;

import in.clouthink.daas.fss.core.StoreFileRequest;
import in.clouthink.daas.fss.core.StoredFileObject;
import in.clouthink.daas.fss.domain.model.FileObject;
import in.clouthink.daas.fss.support.DefaultFileObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author dz
 * @since 3
 */
public class DefaultStoredFileObject extends DefaultFileObject implements StoredFileObject {

    public static DefaultStoredFileObject from(StoreFileRequest request) {
        if (request == null) {
            return null;
        }
        DefaultStoredFileObject result = new DefaultStoredFileObject();
        BeanUtils.copyProperties(request, result);
        return result;
    }

    public static DefaultStoredFileObject from(FileObject fileObject) {
        if (fileObject == null) {
            return null;
        }
        DefaultStoredFileObject result = new DefaultStoredFileObject();
        BeanUtils.copyProperties(fileObject, result);
        return result;
    }

    private static final Log logger = LogFactory.getLog(DefaultStoredFileObject.class);

    private String providerName;

    private ZimgFile zimg;

    @Override
    public String getProviderName() {
        return this.providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public ZimgFile getImplementation() {
        return this.zimg;
    }

    public void setImplementation(ZimgFile zimg) {
        this.zimg = zimg;
    }

    @Override
    public void writeTo(OutputStream outputStream, int bufferSize) throws IOException {
        if (getImplementation() == null) {
            throw new UnsupportedOperationException("The stored file implementation is not supplied.");
        }

        logger.warn(String.format("The bufferSize is not supported in current provider, the value[%d] will be ignored.",
                                  bufferSize));

        zimg.writeTo(outputStream);
    }

    @Override
    public String toString() {
        return "DefaultStoredFileObject{" +
                "providerName='" + providerName + '\'' +
                ", zimg=" + zimg +
                "} " + super.toString();
    }
}

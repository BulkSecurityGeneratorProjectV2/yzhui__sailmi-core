package in.clouthink.daas.fss.support;

import in.clouthink.daas.fss.domain.request.FileObjectSearchRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.Date;

public class FileObjectSearchParam implements FileObjectSearchRequest {

    private int start = 0;

    // limit to 1, max to 100
    private int limit = 20;

    private String sortExpr;

    private String uploadedFilename;

    private String storedFilename;

    private String providerName;

    private Date uploadedAtFrom;

    private Date uploadedAtTo;

    private String uploadedBy;

    @Override
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit < 1) {
            limit = 1;
        }
        if (limit > 100) {
            limit = 100;
        }
        this.limit = limit;
    }

    @Override
    public String getSortExpr() {
        return sortExpr;
    }

    public void setSortExpr(String sortExpr) {
        this.sortExpr = sortExpr;
    }

    @Override
    public String getUploadedFilename() {
        return uploadedFilename;
    }

    public void setUploadedFilename(String uploadedFilename) {
        this.uploadedFilename = uploadedFilename;
    }

    @Override
    public String getStoredFilename() {
        return storedFilename;
    }

    public void setStoredFilename(String storedFilename) {
        this.storedFilename = storedFilename;
    }

    @Override
    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public Date getUploadedAtFrom() {
        return uploadedAtFrom;
    }

    public void setUploadedAtFrom(Date uploadedAtFrom) {
        this.uploadedAtFrom = uploadedAtFrom;
    }

    @Override
    public Date getUploadedAtTo() {
        return uploadedAtTo;
    }

    public void setUploadedAtTo(Date uploadedAtTo) {
        this.uploadedAtTo = uploadedAtTo;
    }

    @Override
    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Override
    public Pageable toPageable() {
        if (StringUtils.isEmpty(this.sortExpr)) {
            return new PageRequest(this.start, this.limit);
        }

        //try parse sort expr
        String[] splitSortExpr = this.sortExpr.split(":");

        if (splitSortExpr.length != 2) {
            return new PageRequest(this.start, this.limit);
        }

        String sortField = splitSortExpr[0];
        Sort.Direction sortDirection = Sort.Direction.fromStringOrNull(splitSortExpr[1]);
        if (sortDirection == null) {
            return new PageRequest(this.start, this.limit);
        }

        return new PageRequest(this.start, this.limit, new Sort(sortDirection, sortField));
    }

}

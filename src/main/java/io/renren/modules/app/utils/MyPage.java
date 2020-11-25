package io.renren.modules.app.utils;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * 分页对象
 */
@Data
public class MyPage {

    private Integer pageNo;//当前页
    private Integer pageSize;//每页查询记录数
    private String sort;//排序方式(asc/desc)
    private String orderBy;//排序字段名
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;//数据起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;//数据结束时间

    public String getSort() {
        if (sort == null) {
            return "asc";
        }
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderBy() {
        if (orderBy == null) {
            return "id";
        }
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isBetween() {
        if (beginDate != null && endDate != null) {
            return true;
        } else {
            return false;
        }
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setPageNo(Integer pageNo) {

        this.pageNo = pageNo;
    }

    public Integer getPageNo() {
        if ((pageNo == null) || (pageNo == 0) || (pageNo < 0)) {
            return 0;
        } else {
            return pageNo;
        }
    }


    public Integer getPageSize() {
        if (pageSize == null) {
            return 20;
        }
        return pageSize;
    }

    public <V> Page<V> converPage() {
        Page<V> userPage = new Page<>();
        userPage.setSize(this.getPageSize());
        userPage.setCurrent(this.getPageNo());
        return userPage;
    }

    public <V> Page<V> converPage(Page page) {
        Page<V> userPage = new Page<>();
        BeanUtils.copyProperties(page, userPage);
        return userPage;
    }

}

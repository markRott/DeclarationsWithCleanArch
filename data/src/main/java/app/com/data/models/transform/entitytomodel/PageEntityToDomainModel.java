package app.com.data.models.transform.entitytomodel;

import app.com.data.models.networkentity.PageEntity;
import app.com.domain.models.PageModel;

public class PageEntityToDomainModel {

    public PageModel transform(final PageEntity pageEntity) {
        PageModel pageModel = new PageModel();
        if (pageEntity != null) {
            pageModel.setBatchSize(pageEntity.getBatchSize());
            pageModel.setCurrentPage(pageEntity.getCurrentPage());
            pageModel.setTotalItems(pageEntity.getTotalItems());
        }
        return pageModel;
    }
}

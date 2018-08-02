package app.com.data.transform;

import app.com.data.models.PageEntity;
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

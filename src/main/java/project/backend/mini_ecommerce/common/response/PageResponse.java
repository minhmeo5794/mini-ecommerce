package project.backend.mini_ecommerce.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class PageResponse<T> {
    private List<T> items;
    private PaginationInfo pagination;

    @Getter
    @Builder
    public static class PaginationInfo {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private int numberOfElements;
        private boolean first;
        private boolean last;
        private boolean empty;
    }

    public static <T> PageResponse<T> from(Page<T> page) {
        return PageResponse.<T>builder()
                .items(page.getContent())
                .pagination(PaginationInfo.builder()
                        .page(page.getNumber())
                        .size(page.getSize())
                        .totalElements(page.getTotalElements())
                        .totalPages(page.getTotalPages())
                        .numberOfElements(page.getNumberOfElements())
                        .first(page.isFirst())
                        .last(page.isLast())
                        .empty(page.isEmpty())
                        .build())
                .build();
    }
}

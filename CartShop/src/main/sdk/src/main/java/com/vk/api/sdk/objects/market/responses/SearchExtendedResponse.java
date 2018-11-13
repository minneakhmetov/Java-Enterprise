package com.vk.api.sdk.objects.market.responses;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.market.MarketItemFull;

import java.util.List;
import java.util.Objects;

/**
 * SearchExtendedResponse object
 */
public class SearchExtendedResponse {
    /**
     * Total number
     */
    @SerializedName("count")
    private Integer count;

    @SerializedName("items")
    private List<MarketItemFull> items;

    public Integer getCount() {
        return count;
    }

    public List<MarketItemFull> getItems() {
        return items;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchExtendedResponse searchExtendedResponse = (SearchExtendedResponse) o;
        return Objects.equals(count, searchExtendedResponse.count) &&
                Objects.equals(items, searchExtendedResponse.items);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchExtendedResponse{");
        sb.append("count=").append(count);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}

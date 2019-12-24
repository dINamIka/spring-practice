package com.yarmak.item;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemManager {

    private List<Item> itemRepository;
    private int maxProcessedItems;
    private String commonItemsName;

}

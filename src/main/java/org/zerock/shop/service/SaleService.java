package org.zerock.shop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.shop.domain.Saleitem.SaleItem;
import org.zerock.shop.domain.Saleitem.SaleItemRepository;
import org.zerock.shop.domain.cartitem.CartItem;
import org.zerock.shop.domain.item.Item;
import org.zerock.shop.domain.item.ItemRepository;
import org.zerock.shop.domain.sale.Sale;
import org.zerock.shop.domain.sale.SaleRepository;
import org.zerock.shop.domain.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final UserPageService userPageService;
    private final SaleItemRepository saleItemRepository;
    private final ItemRepository itemRepository;

    // 회원가입 하면 판매자 당 판매내역 하나 생성
    public void createSale (User user){

        Sale sale = Sale.createSale(user);

        saleRepository.save(sale);
    }

    // id에 해당하는 판매아이템 찾기
    public List<SaleItem> findSellerSaleItems (int sellerId) {

        return saleItemRepository.findSaleItemsBySellerId(sellerId);
    }

    // 판매자 id에 해당하는 Sale 찾기
    public Sale findSaleById (int sellerId) { return saleRepository.findBySellerId(sellerId); }

    // 판매내역에 저장 (장바구니 전체 주문)
    @Transactional
    public SaleItem addSale (int itemId, int sellerId, CartItem cartItem) {

        User seller = userPageService.findUser(sellerId);
        Sale sale = saleRepository.findBySellerId(sellerId);
        sale.setTotalCount(sale.getTotalCount()+cartItem.getCount());
        saleRepository.save(sale);
        SaleItem saleItem = SaleItem.createSaleItem(itemId, sale, seller, cartItem);
        saleItemRepository.save(saleItem);

        return saleItem;
    }

    // 판매내역에 저장 (상품 개별 주문)
    @Transactional
    public SaleItem addSale (int sellerId, Item item, int count) {

        User seller = userPageService.findUser(sellerId);
        Sale sale = saleRepository.findBySellerId(sellerId);
        sale.setTotalCount(sale.getTotalCount()+count);
        saleRepository.save(sale);
        SaleItem saleItem = SaleItem.createSaleItem(item.getId(), sale, seller, item, count);
        saleItemRepository.save(saleItem);

        return saleItem;
    }
}

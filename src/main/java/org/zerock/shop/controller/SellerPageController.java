package org.zerock.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zerock.shop.config.auth.PrincipalDetails;
import org.zerock.shop.domain.Saleitem.SaleItem;
import org.zerock.shop.domain.item.Item;
import org.zerock.shop.domain.sale.Sale;
import org.zerock.shop.service.ItemService;
import org.zerock.shop.service.SaleService;
import org.zerock.shop.service.UserPageService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SellerPageController {
    private final UserPageService userPageService;
    private final ItemService itemService;
    private final SaleService saleService;

    // 판매자 프로필 페이지 접속
    @GetMapping("/seller/{id}")
    public String sellerPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails.getUser().getId() == id) {
            // 로그인이 되어있는 판매자의 id와 판매자 페이지에 접속하는 id가 같아야 함
            model.addAttribute("user", userPageService.findUser(id));

            return "/seller/sellerPage";
        } else {
            return "redirect:/main";
        }

    }

    // 상품 관리 페이지
    @GetMapping("/seller/manage/{id}")
    public String itemManage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUser().getId() == id) {
            // 로그인이 되어있는 판매자의 id와 상품관리 페이지에 접속하는 id가 같아야 함
            List<Item> allItem = itemService.allItemView();
            List<Item> userItem = new ArrayList<>();

            // 자신이 올린 상품만 가져오기
            for(Item item : allItem) {
                if(item.getSeller().getId() == id) {
                    userItem.add(item);
                }
            }

            model.addAttribute("seller", userPageService.findUser(id));
            model.addAttribute("userItem", userItem);

            return "seller/itemManage";
        } else {
            return "redirect:/main";
        }
    }

    // 판매 내역 조회 페이지
    @GetMapping("/seller/salelist/{id}")
    public String saleList(@PathVariable("id")Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 로그인이 되어있는 유저의 id와 판매내역에 접속하는 id가 같아야 함
        if (principalDetails.getUser().getId() == id) {

            Sale sales = saleService.findSaleById(id);
            List<SaleItem> saleItemList = saleService.findSellerSaleItems(id);

            model.addAttribute("sales", sales);
            model.addAttribute("totalCount", sales.getTotalCount());
            model.addAttribute("sellerSaleItems", saleItemList);
            model.addAttribute("seller", userPageService.findUser(id));

            return "seller/saleList";
        }
        else {
            return "redirect:/main";
        }
    }
}

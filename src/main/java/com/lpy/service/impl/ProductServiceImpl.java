package com.lpy.service.impl;

import com.lpy.dao.ProductInfoDao;
import com.lpy.dto.CartDTO;
import com.lpy.entity.ProductInfo;
import com.lpy.enums.ProductStatusEnum;
import com.lpy.enums.RequestEnum;
import com.lpy.exception.SellException;
import com.lpy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 罗鹏远
 * @description: 商品实现类
 * @Date: created in 19:41 2018/8/15
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                log.error("【加库存】 商品不存在，productInfo={}",productInfo);
                throw new SellException(RequestEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(stock);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                log.error("【减库存】 商品不存在，productInfo={}",productInfo);
                throw new SellException(RequestEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(stock < 0){
                log.error("库存不足，productStock={},productQuantity={}",productInfo.getProductStock(),cartDTO.getProductQuantity());
                throw new SellException(RequestEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(stock);
            productInfoDao.save(productInfo);
        }
    }
}

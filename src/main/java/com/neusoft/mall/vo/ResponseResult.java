package com.neusoft.mall.vo;

import com.neusoft.mall.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * 响应发送者
 * Created by chenyingmiao on 17/10/30 AD.
 */
public class ResponseResult<T> {

    private T data;

    public ResponseResult() {
    }

    public ResponseResult(T sendData) {
        this.data = sendData;
    }

    /**
     * 成功，需要将数据响应到前端
     *
     * @return
     */
    public ResponseEntity<Result<T>> success() {
        return new ResponseEntity<Result<T>>((MultiValueMap<String, String>) ResultUtil.success(this.data), HttpStatus.OK);
    }
}

package com.nepxion.discovery.console.endpoint;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.discovery.common.entity.FailoverType;
import com.nepxion.discovery.common.util.ResponseUtil;
import com.nepxion.discovery.console.resource.FailoverResource;

@RestController
@RequestMapping(path = "/failover")
@Api(tags = { "故障转移接口" })
public class FailoverEndpoint {
    @Autowired
    private FailoverResource failoverResource;

    @RequestMapping(path = "/create/{failoverType}/{group}", method = RequestMethod.POST)
    @ApiOperation(value = "全局组订阅方式，创建故障转移", notes = "", response = ResponseEntity.class, httpMethod = "POST")
    @ResponseBody
    public ResponseEntity<?> createFailover(@PathVariable(value = "failoverType") @ApiParam(value = "故障转移类型。取值：version-prefer | version-failover | region-transfer | region-failover | env-failover | zone-failover | address-failover", defaultValue = "version-failover", required = true) String failoverType, @PathVariable(value = "group") @ApiParam(value = "订阅的组名", required = true) String group, @RequestBody @ApiParam(value = "故障转移值，Json格式或者非Json格式", required = true) String failoverValue) {
        return doCreateFailover(failoverType, group, failoverValue);
    }

    @RequestMapping(path = "/clear/{failoverType}/{group}", method = RequestMethod.POST)
    @ApiOperation(value = "全局组订阅方式，清除故障转移", notes = "", response = ResponseEntity.class, httpMethod = "POST")
    @ResponseBody
    public ResponseEntity<?> clearFailover(@PathVariable(value = "failoverType") @ApiParam(value = "故障转移类型。取值：version-prefer | version-failover | region-transfer | region-failover | env-failover | zone-failover | address-failover", defaultValue = "version-failover", required = true) String failoverType, @PathVariable(value = "group") @ApiParam(value = "订阅的组名", required = true) String group) {
        return doClearFailover(failoverType, group);
    }

    @RequestMapping(path = "/create/{failoverType}/{group}/{gatewayId}", method = RequestMethod.POST)
    @ApiOperation(value = "局部网关订阅方式，创建故障转移", notes = "", response = ResponseEntity.class, httpMethod = "POST")
    @ResponseBody
    public ResponseEntity<?> createFailover(@PathVariable(value = "failoverType") @ApiParam(value = "故障转移类型。取值：version-prefer | version-failover | region-transfer | region-failover | env-failover | zone-failover | address-failover", defaultValue = "version-failover", required = true) String failoverType, @PathVariable(value = "group") @ApiParam(value = "订阅的组名", required = true) String group, @PathVariable(value = "gatewayId") @ApiParam(value = "订阅的网关名", required = true) String gatewayId, @RequestBody @ApiParam(value = "故障转移值，Json格式或者非Json格式", required = true) String failoverValue) {
        return doCreateFailover(failoverType, group, gatewayId, failoverValue);
    }

    @RequestMapping(path = "/clear/{failoverType}/{group}/{gatewayId}", method = RequestMethod.POST)
    @ApiOperation(value = "局部网关订阅方式，清除故障转移", notes = "", response = ResponseEntity.class, httpMethod = "POST")
    @ResponseBody
    public ResponseEntity<?> clearFailover(@PathVariable(value = "failoverType") @ApiParam(value = "故障转移类型。取值：version-prefer | version-failover | region-transfer | region-failover | env-failover | zone-failover | address-failover", defaultValue = "version-failover", required = true) String failoverType, @PathVariable(value = "group") @ApiParam(value = "订阅的组名", required = true) String group, @PathVariable(value = "gatewayId") @ApiParam(value = "订阅的网关名", required = true) String gatewayId) {
        return doClearFailover(failoverType, group, gatewayId);
    }

    private ResponseEntity<?> doCreateFailover(String failoverType, String group, String failoverValue) {
        try {
            String result = failoverResource.createFailover(FailoverType.fromString(failoverType), group, failoverValue);

            return ResponseUtil.getSuccessResponse(result);
        } catch (Exception e) {
            return ResponseUtil.getFailureResponse(e);
        }
    }

    private ResponseEntity<?> doClearFailover(String failoverType, String group) {
        try {
            String result = failoverResource.clearFailover(FailoverType.fromString(failoverType), group);

            return ResponseUtil.getSuccessResponse(result);
        } catch (Exception e) {
            return ResponseUtil.getFailureResponse(e);
        }
    }

    private ResponseEntity<?> doCreateFailover(String failoverType, String group, String gatewayId, String failoverValue) {
        try {
            String result = failoverResource.createFailover(FailoverType.fromString(failoverType), group, gatewayId, failoverValue);

            return ResponseUtil.getSuccessResponse(result);
        } catch (Exception e) {
            return ResponseUtil.getFailureResponse(e);
        }
    }

    private ResponseEntity<?> doClearFailover(String failoverType, String group, String gatewayId) {
        try {
            String result = failoverResource.clearFailover(FailoverType.fromString(failoverType), group, gatewayId);

            return ResponseUtil.getSuccessResponse(result);
        } catch (Exception e) {
            return ResponseUtil.getFailureResponse(e);
        }
    }
}
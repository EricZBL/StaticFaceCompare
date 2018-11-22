package com.hzgc.manage.controller;

import com.hzgc.jniface.BigPictureData;
import com.hzgc.bean.SearchOption;
import com.hzgc.manage.dto.PersonDto;
import com.hzgc.manage.dto.PersonQueryDto;
import com.hzgc.manage.entity.Person;
import com.hzgc.manage.service.PersonService;
import com.hzgc.manage.vo.ResultVO;
import com.hzgc.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.directory.SearchResult;

/**
 * 人口库服务web层
 * created by liang on 18-11-20
 */
@RestController
@Api(value = "/person", tags = "人口库服务")
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;


    @ApiOperation(value = "post请求",notes="人口分页列表")
    @ApiImplicitParam(dataType = "personQueryDto",name = "personQueryDto",value = "分页模糊匹配参数",required = true)
    @RequestMapping(value = "pageList", method = RequestMethod.POST)
    public ResultVO<Page> pageList(@RequestBody PersonQueryDto personQueryDto){

        Pageable pageable = PageRequest.of(personQueryDto.getPage(), personQueryDto.getSize());
        Page<Person> page = personService.findPageByXmSfz(personQueryDto, pageable);
        return ResultUtils.success(page);
    }

    @ApiOperation(value = "post请求",notes="人口新增")
    @ApiImplicitParam(dataType = "PersonDto",name = "personDto",value = "人口新增",required = true)
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultVO<String> insert(@RequestBody PersonDto personDto) {
        personService.insert(personDto);
        return ResultUtils.success();
    }

    @ApiOperation(value = "get请求",notes="单一人口查询")
    @ApiImplicitParam(dataType = "String",name = "id",value = "人口id",required = true)
    @RequestMapping(value = "info", method = RequestMethod.POST)
    public ResultVO<Person> info(@RequestParam("id") String id) {
        Person person = personService.findById(id);
        return ResultUtils.success(person);
    }


    @ApiOperation(value = "post请求",notes="人口修改")
    @ApiImplicitParam(dataType = "PersonDto",name = "personDto",value = "人口信息名称",required = true)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResultVO<String> update(@RequestBody PersonDto personDto) {
        personService.update(personDto);
        return ResultUtils.success();
    }

    @ApiOperation(value = "delete请求",notes="根据人口ID删除")
    @ApiImplicitParam(dataType = "String",name = "id",value = "人口id",required = true)
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResultVO<String> delete(@RequestParam("id") String id) {
        personService.deleteById(id);
        return ResultUtils.success();
    }

//    @ApiOperation(value = "行人属性提取", response = CaptureResult.class)
//    @RequestMapping(value = "person_capture", method = RequestMethod.POST)
//    public ResultVO<CaptureResult> personFeatureExtract(@ApiParam(name = "image", value = "图片") MultipartFile image) {
//
//        CaptureResult captureResult = new CaptureResult();
//
//        return ResultUtils.success(captureResult);
//    }

//    /*
//     *人臉base64提取特征
//     */
//    @ApiIgnore(value = "内部调用的人脸检测接口,入参为图片的Base64字符串")
//    @RequestMapping(value = BigDataPath.FEATURE_CHECK_BASE64, method = RequestMethod.POST)
//    public ResponseEntity <PictureData> faceFeatureCheck_base64(@RequestBody String baseStr) {
//        PictureData pictureData = faceExtractService.featureCheckByImage(baseStr);
//        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(pictureData);
//    }

    /*
     * 人臉
     */
    @ApiOperation(value = "人脸特征值提取", response = BigPictureData.class)
    @RequestMapping(value = "/extract_picture", method = RequestMethod.POST)
    public ResultVO<BigPictureData> faceFeatureExtract(@ApiParam(name = "image", value = "图片") MultipartFile image) {
        byte[] imageBin = null;
        if (image == null) {
//            log.error("Start extract feature by binary, image is null");
//            return ResponseResult.error(RestErrorCode.ILLEGAL_ARGUMENT);
        }
//        try {
//            imageBin = image.getBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //邏輯
//       // BigPictureData bigPictureData = faceExtractService.featureExtractByImage(imageBin);
//        if (null == bigPictureData) {
//            return ResponseResult.error(RestErrorCode.ILLEGAL_ARGUMENT, "提取不到特征值");
//        }
        BigPictureData bigPictureData = new BigPictureData();
        return ResultUtils.success(bigPictureData);
    }

    @ApiOperation(value = "以图搜图", response = SearchResult.class)
    @RequestMapping(value = "/search_picture", method = RequestMethod.POST)
    public ResultVO<SearchResult> searchPicture(
            @RequestBody @ApiParam(value = "以图搜图查询参数") SearchOption searchOption) {
        SearchResult searchResult;
        if (searchOption == null) {
//            log.error("Start search picture, but search option is null");
//            return ResponseResult.error(RestErrorCode.ILLEGAL_ARGUMENT);
        }

        if (searchOption.getImages() == null) {
//            log.error("Start search picture, but images is null");
//            return ResponseResult.error(RestErrorCode.ILLEGAL_ARGUMENT);
        }

        if (searchOption.getSimilarity() < 0.0) {
//            log.error("Start search picture, but threshold is error");
//            return ResponseResult.error(RestErrorCode.ILLEGAL_ARGUMENT);
        }
//        Map<String, Device> ipcMapping = DeviceToIpcs.getIpcMapping(searchOption.getDeviceIpcs());
//        searchOption.setIpcMapping(ipcMapping);
//        if (searchOption.getDeviceIpcs() == null
//                || searchOption.getDeviceIpcs().size() <= 0
//                || searchOption.getDeviceIpcs().get(0) == null) {
//            log.error("Start search picture, but deviceIpcs option is error");
//            return ResponseResult.error(RestErrorCode.ILLEGAL_ARGUMENT);
//        }
//        log.info("Start search picture, set search id");
//        String searchId = UuidUtil.getUuid();
//        log.info("Start search picture, search option is:" + JacksonUtil.toJson(searchOption));
//        searchResult = captureSearchService.searchPicture2(searchOption, searchId);
//        return ResponseResult.init(searchResult);


        return  new ResultVO<SearchResult>();
    }
}

package com.money.custom.controller;

import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@VisitLogFlag(type = VisitLogTypeEnum.IMPORT)
@Controller
@RequestMapping("/uploadExcel/*")
public class UploadController extends BaseController {


}

package com.hyq.lpg.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hyq.lpg.entity.Tanktransfer;
import com.hyq.lpg.service.TanktransferService;
@Controller
@RequestMapping(value="/transfer")
public class TanktransferController {
	private TanktransferService tanktransferService;
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult */
	@RequestMapping(method=RequestMethod.POST)
	public  org.springframework.http.ResponseEntity<Object> create(ModelMap model,RedirectAttributes redirectAttributes,
					@Valid Tanktransfer tanktransfer,BindingResult errors,
					HttpServletRequest request,
					HttpServletResponse response)
							throws Exception {
		if(errors.hasErrors()) {
			return new ResponseEntity("你失败了 ："  + errors.getErrorCount(),org.springframework.http.HttpStatus.BAD_REQUEST);

		}
		tanktransferService.add(tanktransfer);
		return new ResponseEntity("你成功了",org.springframework.http.HttpStatus.OK);
	}
	
	
	
	@Autowired
	public void setTanktransferService(TanktransferService tanktransferService) {
		this.tanktransferService = tanktransferService;
	}
	
	
	
	
	

}

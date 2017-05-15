package org.bonn.soa;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author philipp.amkreutz
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ApplicantRepository applicantRepository;

	@RequestMapping(value="/start-hire-process", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
	public void startHireProcess(@RequestBody Map<String, String> data) {

		Applicant applicant = new Applicant(data.get("name"), data.get("email"), data.get("phoneNumber"));
		applicantRepository.save(applicant);

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("applicant", applicant);
		runtimeService.startProcessInstanceByKey("hireProcessWithJpa", variables);
	}

}

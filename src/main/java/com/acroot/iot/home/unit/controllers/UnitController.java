package com.acroot.iot.home.unit.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acroot.iot.home.unit.RequestModels.RemoteSwitchesModel;
import com.acroot.iot.home.unit.RequestModels.SlaveModel;
import com.acroot.iot.home.unit.RequestModels.SwitchModel;
import com.acroot.iot.home.unit.ResponseModels.RemoteSwitchesResponseModel;
import com.acroot.iot.home.unit.ResponseModels.SlaveResponseModel;
import com.acroot.iot.home.unit.ResponseModels.SwitchResponseModel;
import com.acroot.iot.home.unit.entities.RemoteSwitchesEntity;
import com.acroot.iot.home.unit.entities.SlaveEntity;
import com.acroot.iot.home.unit.entities.SwitchEntity;
import com.acroot.iot.home.unit.repositories.RemoteSwitchesRepository;
import com.acroot.iot.home.unit.repositories.SlaveRepository;
import com.acroot.iot.home.unit.repositories.SwitchRepository;

@Controller
public class UnitController {
	
	@Autowired
	SlaveRepository slaveRepository;
	
	@Autowired
	SwitchRepository switchRepository;
	
	@Autowired
	RemoteSwitchesRepository remoteSwitchesRepository;
	
	
	@RequestMapping(path="/addSlave", method=RequestMethod.POST)
	String addSlaves(@RequestBody SlaveModel slaveModel) 
	{
		SlaveEntity slaveEntity=new SlaveEntity();
		slaveEntity.setSlaveUID(slaveModel.getSlaveUID());
		slaveEntity.setIpAddress(slaveModel.getIpAddress());
		slaveEntity.setSecretKey(slaveModel.getSecretKey());
		slaveEntity.setHealth(slaveModel.isHealth());
		slaveRepository.save(slaveEntity);
		return "";
		
	}
	
	@RequestMapping(path="/addSwitches", method=RequestMethod.POST)
	String addSwitches(@RequestBody SwitchModel switchModel)
	{
		SwitchEntity switchEntity=new SwitchEntity();;
		switchEntity.setSwitchUid(switchModel.getSwitchUid());
		switchEntity.setTag(switchModel.getTag());
		switchEntity.setStatus(switchModel.isStatus());
		switchRepository.save(switchEntity);
				
		return "";
		
	}
	
	@RequestMapping(path="/addRemoteSwitches", method=RequestMethod.POST)
	String addRemoteSwitches(@RequestBody RemoteSwitchesModel remoteSwitchesModel)
	{
		RemoteSwitchesEntity remoteSwitchesEntity=new RemoteSwitchesEntity();
		remoteSwitchesEntity.setSwitchUid(remoteSwitchesModel.getSwitchUid());
		remoteSwitchesEntity.setTag(remoteSwitchesModel.getTag());
		remoteSwitchesEntity.setStatus(remoteSwitchesModel.isStatus());
		remoteSwitchesRepository.save(remoteSwitchesEntity);
		return "";
		
	}
	// To get the status of switch
	@RequestMapping(path="/{switchId}", method=RequestMethod.GET)
	ResponseEntity<SwitchResponseModel> showSwitchStatus(@PathVariable Long switchId)
	{
		
		SwitchEntity switchEntity=switchRepository.getOne(switchId);
		if(switchEntity!=null)
		{
			boolean status=switchEntity.isStatus();
			SwitchResponseModel switchResponseModel=new SwitchResponseModel();
			switchResponseModel.setStatus(status);
			ResponseEntity<SwitchResponseModel> responseEntity=new ResponseEntity<>(switchResponseModel,HttpStatus.FOUND);
			return responseEntity;
		}
		else
		{	
			SwitchResponseModel switchResponseModel=new SwitchResponseModel();
			switchResponseModel.setErrorMessage("Invalid SwitchId");
			ResponseEntity<SwitchResponseModel> responseEntity=new ResponseEntity<>(switchResponseModel,HttpStatus.NOT_FOUND);
			
			return responseEntity;
		}
		
	}
	
	
	//To get all the switches of a slave
	
	@RequestMapping(path="/{slaveId}",method=RequestMethod.GET)
	ResponseEntity<?> showAllSwitches(@PathVariable Long slaveId)
	{
		SlaveEntity slaveEntity=slaveRepository.getOne(slaveId);
		if(slaveEntity!=null)
		{
			RemoteSwitchesEntity remoteSwitchesEntity =remoteSwitchesRepository.getOne(slaveId);
			if(remoteSwitchesEntity!=null)
			{
				RemoteSwitchesResponseModel remoteSwitchesResponseModel = new RemoteSwitchesResponseModel();
				remoteSwitchesResponseModel.setSwitchId(remoteSwitchesEntity.getSwitchId());
				remoteSwitchesResponseModel.setSwitchUid(remoteSwitchesEntity.getSwitchUid());
				remoteSwitchesResponseModel.setStatus(remoteSwitchesEntity.isStatus());
				remoteSwitchesResponseModel.setTag(remoteSwitchesEntity.getTag());
				remoteSwitchesResponseModel.setErrorMessage("Its a rempte slave");
				ResponseEntity<RemoteSwitchesResponseModel> responseEntity=new ResponseEntity<>(remoteSwitchesResponseModel,HttpStatus.OK);
				return responseEntity;
			}
			else
			{
			List<SwitchEntity> switchEntity = switchRepository.findAll();
			//List<SwitchEntity> switchEntities=new ArrayList<SwitchEntity>();
			for(SwitchEntity n: switchEntity)
			{
				SwitchResponseModel switchResponseModel=new SwitchResponseModel();	
				switchResponseModel.setSwitchId(n.getSwitchId());
				switchResponseModel.setSwitchUid(n.getSwitchUid());
				switchResponseModel.setStatus(n.isStatus());
				switchResponseModel.setErrorMessage("Not a remote slave");
				switchResponseModel.setTag(n.getTag());
				ResponseEntity<SwitchResponseModel> responseEntity=new ResponseEntity<>(switchResponseModel,HttpStatus.OK);
				return responseEntity;
			}
			}	
		}
		return null;
		
	
		
		
	}
	
	
	/*
	 * List<RemoteSwitchesEntity> showAllSwitchStatus(@PathVariable Long slaveId) {
	 * SlaveEntity slaveEntity = slaveRepository.findById(slaveId).get(); return
	 * slaveEntity.getRemoteSwitchesEntities();
	 * 
	 * }
	 */
	
	
	// To find the health of a particular slave
	@RequestMapping(path="/{slaveId}/health",method=RequestMethod.GET)
	ResponseEntity<SlaveResponseModel> showHealth(@PathVariable Long slaveId)
	{
		SlaveEntity slaveEntity=slaveRepository.getOne(slaveId);
		
		if(slaveEntity!=null)
		{
			SlaveResponseModel slaveResponseModel=new SlaveResponseModel();
			slaveResponseModel.setHealth(slaveEntity.isHealth());
			slaveResponseModel.setSlaveId(slaveEntity.getSlaveId());
			ResponseEntity<SlaveResponseModel> responseEntity=new ResponseEntity<>(slaveResponseModel,HttpStatus.OK);
			return responseEntity;
		}
		else
		{
			SlaveResponseModel slaveResponseModel=new SlaveResponseModel();
			slaveResponseModel.setErrorMessage("Invalid SlaveId");
			ResponseEntity<SlaveResponseModel> responseEntity=new ResponseEntity<>(slaveResponseModel,HttpStatus.NOT_FOUND);
			return responseEntity;

		}
	}
	
	// To set the status of a particular switch
	@RequestMapping(path="/{switchId}/{state}", method=RequestMethod.POST)
	ResponseEntity<SwitchResponseModel> setSwitchStatus(@PathVariable Boolean state,@PathVariable Long switchId)
	{
		
		SwitchEntity switchEntity=switchRepository.getOne(switchId);
		if(switchEntity!=null)
		{
		switchEntity.setStatus(state);
		switchRepository.save(switchEntity);
		
		SwitchResponseModel switchResponseModel = new SwitchResponseModel();
		switchResponseModel.setSwitchUid(switchEntity.getSwitchUid());
		switchResponseModel.setStatus(switchEntity.isStatus());
		switchResponseModel.setSwitchId(switchEntity.getSwitchId());
		
		ResponseEntity<SwitchResponseModel> responseEntity=new ResponseEntity<>(switchResponseModel,HttpStatus.CREATED);
		
		return responseEntity;
		}
		else
		{
			SwitchResponseModel switchResponseModel=new SwitchResponseModel();
			switchResponseModel.setErrorMessage("Invalid switch Id");
			ResponseEntity<SwitchResponseModel> responseEntity=new ResponseEntity<>(switchResponseModel,HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}
	
	// To set a remote switch status
	
	@RequestMapping(path="/remote/{switchId}/{state}", method=RequestMethod.POST)
	ResponseEntity<RemoteSwitchesResponseModel> setRemoteSwitchStatus(@PathVariable Boolean state,@PathVariable Long switchId)
	{
		
		RemoteSwitchesEntity remoteSwitchesEntity=remoteSwitchesRepository.getOne(switchId);
		if(remoteSwitchesEntity!=null)
		{
		remoteSwitchesEntity.setStatus(state);
		remoteSwitchesRepository.save(remoteSwitchesEntity);
		
		RemoteSwitchesResponseModel remoteSwitchesResponseModel = new RemoteSwitchesResponseModel();
		remoteSwitchesResponseModel.setSwitchUid(remoteSwitchesEntity.getSwitchUid());
		remoteSwitchesResponseModel.setStatus(remoteSwitchesEntity.isStatus());
		remoteSwitchesResponseModel.setSwitchId(remoteSwitchesEntity.getSwitchId());
		
		ResponseEntity<RemoteSwitchesResponseModel> responseEntity=new ResponseEntity<>(remoteSwitchesResponseModel,HttpStatus.CREATED);
		
		return responseEntity;
		}
		else
		{
			RemoteSwitchesResponseModel remoteSwitchesResponseModel=new RemoteSwitchesResponseModel();
			remoteSwitchesResponseModel.setErrorMessage("Invalid switch Id");
			ResponseEntity<RemoteSwitchesResponseModel> responseEntity=new ResponseEntity<>(remoteSwitchesResponseModel,HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

// To get the remote switch status
	
	@RequestMapping(path="/remote/{switchId}", method=RequestMethod.GET)
	ResponseEntity<RemoteSwitchesResponseModel> getRemoteSwitchStatus(@PathVariable Long switchId)
	{
		
		RemoteSwitchesEntity remoteSwitchesEntity=remoteSwitchesRepository.getOne(switchId);
		if(remoteSwitchesEntity!=null)
		{
					
		RemoteSwitchesResponseModel remoteSwitchesResponseModel = new RemoteSwitchesResponseModel();
		remoteSwitchesResponseModel.setSwitchUid(remoteSwitchesEntity.getSwitchUid());
		remoteSwitchesResponseModel.setStatus(remoteSwitchesEntity.isStatus());
		remoteSwitchesResponseModel.setSwitchId(remoteSwitchesEntity.getSwitchId());
		
		ResponseEntity<RemoteSwitchesResponseModel> responseEntity=new ResponseEntity<>(remoteSwitchesResponseModel,HttpStatus.FOUND);
		
		return responseEntity;
		}
		else
		{
			RemoteSwitchesResponseModel remoteSwitchesResponseModel=new RemoteSwitchesResponseModel();
			remoteSwitchesResponseModel.setErrorMessage("Invalid switch Id");
			ResponseEntity<RemoteSwitchesResponseModel> responseEntity=new ResponseEntity<>(remoteSwitchesResponseModel,HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}
	
//To get the health of a remote slave
	@RequestMapping(path="/remote/{slaveId}/health",method=RequestMethod.GET)
	ResponseEntity<SlaveResponseModel> showRemoteHealth(@PathVariable Long slaveId)
	{
		SlaveEntity slaveEntity=slaveRepository.getOne(slaveId);
		
		if(slaveEntity!=null)
		{
			SlaveResponseModel slaveResponseModel=new SlaveResponseModel();
			slaveResponseModel.setHealth(slaveEntity.isHealth());
			slaveResponseModel.setSlaveId(slaveEntity.getSlaveId());
			ResponseEntity<SlaveResponseModel> responseEntity=new ResponseEntity<>(slaveResponseModel,HttpStatus.OK);
			return responseEntity;
		}
		else
		{
			SlaveResponseModel slaveResponseModel=new SlaveResponseModel();
			slaveResponseModel.setErrorMessage("Invalid SlaveId");
			ResponseEntity<SlaveResponseModel> responseEntity=new ResponseEntity<>(slaveResponseModel,HttpStatus.NOT_FOUND);
			return responseEntity;

		}
	}
	
}



package com.iaddress.api.rest.basic.location.data.repo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.iaddress.api.rest.basic.location.data.model.LocationModel;

@Named
public class LocationRepository {

	@Inject
	private MongoTemplate mongoTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createCollection(Class collectionClass) {
		if (!mongoTemplate.collectionExists(collectionClass)) {
			mongoTemplate.createCollection(collectionClass);
			mongoTemplate.indexOps(collectionClass).ensureIndex(
					new GeospatialIndex("geoCode"));
		}
	}

	public void addLocation(LocationModel location) {
		createCollection(LocationModel.class);
		mongoTemplate.insert(location);
	}

	public List<LocationModel> retrieveLocation(LocationModel location) {
		return mongoTemplate.find(new Query(Criteria.where("geoCode").is(location.getGeoCode())), LocationModel.class);
	}

	public LocationModel retrieveIAddress(LocationModel location) {
		return mongoTemplate.findOne(new Query(Criteria.where("iAddress").is(location.getiAddress())), LocationModel.class);
	}
	
	public List<LocationModel> retrieveLocationByMobile(LocationModel location) {
		return mongoTemplate.find(new Query(Criteria.where("mobile").is(location.getMobile())), LocationModel.class);
	}
}

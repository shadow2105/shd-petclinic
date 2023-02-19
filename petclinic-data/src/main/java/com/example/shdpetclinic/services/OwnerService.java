package com.example.shdpetclinic.services;

import com.example.shdpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}

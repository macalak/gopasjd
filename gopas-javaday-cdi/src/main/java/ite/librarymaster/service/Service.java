package ite.librarymaster.service;

import ite.librarymaster.interceptor.Monitor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

import javax.enterprise.inject.Stereotype;
import javax.transaction.Transactional;

@Stereotype
@Transactional
@Monitor
@Target(TYPE)
@Retention(RUNTIME)
public @interface Service {

}


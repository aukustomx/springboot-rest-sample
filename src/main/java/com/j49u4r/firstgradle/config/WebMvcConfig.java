/*
 * Licencia:    Este código se encuentra bajo la protección
 *              que otorga el contrato establecido entre
 *              Interware SA de CV y su cliente, Kuspit por lo
 *              que queda estrictamente prohibido copiar, donar
 *              vender y/o distribuir el presente código por
 *              cualquier medio electrónico o impreso sin el
 *              permiso explícito y por escrito del cliente.
 *
 * Proyecto:    SaludCercana
 * Paquete:     com.sc.config
 * Modulo:      WebMvcConfig
 * Tipo:        clase
 * Autor:       Gustavo Adolfo Arellano Sandoval (GAA)
 * Fecha:       Tuesday 05 de May de 2016 (21_24)
 * Version:     0.0.1
 * .
 * Clase que se usa para agregar los Handlers MVC para recursos adicionales como Swagger y React
 *
 * Historia:    .
 *              20160524_2124 Generado por GOOSE
 *
 *
 */
package com.j49u4r.firstgradle.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>WebMvcConfig class.</p>
 *
 * @author gustavo arellano
 * @version $Id: $Id
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  /**
   * {@inheritDoc}
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");

    registry
        .addResourceHandler("/resources/**")
        .addResourceLocations("(/resources/");

    registry
        .addResourceHandler("/**")
        .addResourceLocations("classpath:/META-INF/resources/");
  }

}

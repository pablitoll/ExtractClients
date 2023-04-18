package com.rp.crm;

import java.io.IOException;
import java.io.Writer;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.rp.crm.model.ClientePlataforma;
import com.rp.crm.model.ClientePlataformaRowMapper;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;
	@Resource
	 public Environment environment;
	 @Bean
	 public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
	     return new PropertySourcesPlaceholderConfigurer();
	 }
	@Bean
	public DataSource dataSource() throws Exception {
		
    	final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		// dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=RP;integratedSecurity=true;");
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean
	public JdbcCursorItemReader<ClientePlataforma> reader() {

		JdbcCursorItemReader<ClientePlataforma> reader = new JdbcCursorItemReader<ClientePlataforma>();
		reader.setDataSource(dataSource);
		String sql = "SELECT  CLIE_CLIENTE as plataforma_id     \r\n" + 
				"      ,UPPER(REPLACE( replace( replace ( replace  (replace( replace (CLIE_NOMBRE, 'á', 'a' ), 'é', 'e' ), 'í', 'i' ), 'ó', 'o' ), 'ú', 'u' ), ',' , '-' )) as NAME \r\n" + 
				"      ,UPPER(REPLACE( replace( replace ( replace  (replace( replace (CLIE_NOMBRE_LEGAL, 'á', 'a' ), 'é', 'e' ), 'í', 'i' ), 'ó', 'o' ), 'ú', 'u' ), ',' , '-' )) as razon_social_c\r\n" + 
				"	  ,UPPER(REPLACE( replace( replace ( replace  (replace( replace (CLIE_TELEFONO, 'á', 'a' ), 'é', 'e' ), 'í', 'i' ), 'ó', 'o' ), 'ú', 'u' ), ',' , '-' )) as phone_office\r\n" + 
				"	  ,UPPER(REPLACE( replace( replace ( replace  (replace( replace (CLIE_DOMICILIO, 'á', 'a' ), 'é', 'e' ), 'í', 'i' ), 'ó', 'o' ), 'ú', 'u' ), ',' , '-' )) as billing_address_street\r\n" + 
				"	  ,UPPER(REPLACE(CLIE_LOCALIDAD,',' ,'-' )) as billing_address_city\r\n" + 
				"	  ,UPPER(REPLACE( replace( replace ( replace  (replace( replace (PCIA_NOMBRE, 'á', 'a' ), 'é', 'e' ), 'í', 'i' ), 'ó', 'o' ), 'ú', 'u' ), ',' , '-' )) as billing_address_state\r\n" + 
				"	  ,CLIE_COD_POSTAL as billing_address_postalcode\r\n" + 
				"	  , pais.PAIS_NOMBRE  as billing_address_country\r\n" + 
				"	  , usu.CLC4_NOMBRE as assinged_to\r\n" + 
				"	  , ct.TCLI_NOMBRE as tipo_rp_c --CUSTOM 3\r\n" + 
				"	  ,UPPER(REPLACE( replace( replace ( replace  (replace( replace (ca.ACLI_NOMBRE , 'á', 'a' ), 'é', 'e' ), 'í', 'i' ), 'ó', 'o' ), 'ú', 'u') , ',' , '-' )) as industria_rp_c --custom\r\n" + 
				"	  , CLIE_CLASIF_2 as frecuencia_compra_c\r\n" + 
				"	  ,  '2021-06-27 14:29:36'  as date_modified\r\n" + 
		//		"  FROM  rp.dbo.CCOB_CLIE as cliente    , rp.dbo.SIST_PCIA as provincias     , rp.dbo.SIST_PAIS as pais     , rp.dbo.CCOB_ACLI as ca      , rp.dbo.CCOB_TCLI as ct     , rp.dbo.CCOB_CLC4 as usu \r\n" +
				"  FROM  rpaper.dbo.CCOB_CLIE as cliente, rpaper.dbo.SIST_PCIA as provincias , rpaper.dbo.SIST_PAIS as pais , rpaper.dbo.CCOB_ACLI as ca  , rpaper.dbo.CCOB_TCLI as ct , rpaper.dbo.CCOB_CLC4 as usu  \r\n" + 
				"  where cliente.CLIE_PROVINCIA = provincias.PCIA_PROVINCIA\r\n" + 
				"    and cliente.CLIE_PAIS= pais.PAIS_PAIS\r\n" + 
				"    and cliente.CLIE_TIPO_CLI = ct.TCLI_TIPO_CLI "+
				"    and cliente.CLIE_CLASIF_4 = usu.CLC4_CLASIF_4 "+
				"    and cliente.CLIE_ACTIVIDAD_CLI = ca.ACLI_ACTIVIDAD_CLI " ;
		
		if (environment.containsProperty("client")) {
			System.out.println("Procesando client-->" + environment.getProperty("client"));
			sql = sql + " and cliente.CLIE_CLIENTE = " +  environment.getProperty("client");  
		}
		
		reader.setSql(sql);
		reader.setRowMapper(new ClientePlataformaRowMapper());
		return reader;

	}

	public ClientePlataformaItemProcessor processor() {
		return new ClientePlataformaItemProcessor();
	}

	@Bean
	public FlatFileItemWriter<ClientePlataforma> writer() throws Exception {
		FlatFileItemWriter<ClientePlataforma> writer = new FlatFileItemWriter<ClientePlataforma>();
		String path = BatchConfiguration.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = environment.getProperty("filePath");
		writer.setResource(new FileSystemResource(decodedPath + "clienteplataforma.csv"));
		writer.setHeaderCallback(new FlatFileHeaderCallback() {
			public void writeHeader(Writer writer) throws IOException {
				
				String[] headers = new String[] { 
						"plataforma_id",
						"nombre" ,
						"razonSocial",
						"telefono", 
						"domicilio",
						"localidad",
						"provincia",
						"codigoPostal",
						"pais",
						"asignadoA",
						"tipoCliente",
						"industria",
						"frecuenciaCompra"};
				for (String header : headers) {
					if (header.contentEquals(headers[headers.length-1]))
					{  writer.write( header ); }
					else {writer.write(header + ",");}
					
				}
			}

		});

		writer.setLineAggregator(new DelimitedLineAggregator<ClientePlataforma>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<ClientePlataforma>() {
					{
						setNames(new String[] { 	
								"plataforma_id",
								"nombre" ,
								"razonSocial",
								"telefono", 
								"domicilio",
								"localidad",
								"provincia",
								"codigoPostal",
								"pais",
								"asignadoA",
								"tipoCliente",
								"industria",
								"frecuenciaCompra" });
					}
				});
			}
		});
		return writer;
	}

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1").<ClientePlataforma, ClientePlataforma>chunk(10).reader(reader())
				.processor(processor()).writer(writer()).build();

	}

	@Bean
	public Job exportClientePlataformaJob() throws Exception {
		return jobBuilderFactory.get("exportClientePlataformaJob").incrementer(new RunIdIncrementer()).flow(step1())
				.end().build();

	}

}

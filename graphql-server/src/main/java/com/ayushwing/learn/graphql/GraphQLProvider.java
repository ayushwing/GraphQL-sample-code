package com.ayushwing.learn.graphql;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQLProvider {

	@Autowired
	private GraphQLDataFetchers graphQLDataFetchers;

	@Bean
	public GraphQL graphQL() throws IOException {
		return init();
	}

	public GraphQL init() throws IOException {
		URL url = Resources.getResource("schema.graphqls");
		String sdl = Resources.toString(url, Charsets.UTF_8);
		GraphQLSchema graphQLSchema = buildSchema(sdl);
		return GraphQL.newGraphQL(graphQLSchema).build();
	}

	private GraphQLSchema buildSchema(String sdl) {
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

	}

	private RuntimeWiring buildWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type(newTypeWiring("Query").dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
				.type(newTypeWiring("Book").dataFetcher("author", graphQLDataFetchers.getAutherDataFetcher())).build();
	}

}

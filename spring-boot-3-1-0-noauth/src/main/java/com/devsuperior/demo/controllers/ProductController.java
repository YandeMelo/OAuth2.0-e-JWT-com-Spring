package com.devsuperior.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.demo.dto.ProductDTO;
import com.devsuperior.demo.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(summary = "Buscar todos os produtos")
   	@ApiResponse(responseCode = "200", description = "Status 200 OK") 
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> list = productService.findAll();
		return ResponseEntity.ok(list);
	}
	
    @Operation(summary = "Buscar produto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status 200 OK"),
        @ApiResponse(responseCode = "400", description = "Status 403 Forbidden"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = productService.findById(id);
        return ResponseEntity.ok(dto);
    }
	
    @Operation(summary = "Inserir um novo produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status 200 OK"),
        @ApiResponse(responseCode = "401", description = "Status 401 Unauthorized"),
    })
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        dto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}

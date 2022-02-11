package com.christian.app.Controller;

import com.christian.app.Entity.Product;
import com.christian.app.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<?> create (@RequestBody Product product){
        if(product.getPrecio()>0&&product.getCantidad()>0
                &&String.valueOf(product.getPrecio()).length()-String.valueOf(product.getPrecio()).indexOf(".")<=3){

            double vcalculo=0;
            double vcompra=product.getCantidad()*product.getPrecio();
            if(vcompra>50){
                vcalculo=vcompra-(vcompra*0.1);
                product.setCalculo(vcompra);
            }else{
                product.setCalculo(vcompra);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product)+"\nPRODUCTO REGISTRADO"+"\nDESCUENTO: "+(vcompra*0.1)+
                    "\nIVA: "+vcompra*0.12+"\nTOTAL: "+(vcompra-(vcompra*0.1)+(vcompra*0.12)));
        } else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value="id") Long productId){
        Optional<Product> optionalProduct=productService.findById(productId);
        if(!optionalProduct.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalProduct);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Product productDetails, @PathVariable(value = "id") Long productId){
        Optional<Product> product =productService.findById(productId);
        if(!product.isPresent()){
            return ResponseEntity.notFound().build();
        }
        product.get().setCantidad(productDetails.getCantidad());
        product.get().setDescripcion(productDetails.getDescripcion());
        product.get().setPrecio(productDetails.getPrecio());

        if(product.get().getPrecio()>0&&product.get().getCantidad()>0){
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product.get()));
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long productId){
        if(!productService.findById(productId).isPresent()){
            return ResponseEntity.notFound().build();
        }
        productService.deleteById(productId);
        return ResponseEntity.ok().build();
    }
    //Read all Users
    @GetMapping
    public List<Product> readAll(){
        List<Product> products= StreamSupport.stream(productService.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return products;
    }

}

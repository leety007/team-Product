package rentalService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import rentalService.config.kafka.KafkaProcessor;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    
    @Autowired
    ProductRepository ProductRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverProductSaved_Product(@Payload ProductSaved productSaved){

        if(productSaved.isMe()){

            Product product = new Product();
            BeanUtils.copyProperties(productSaved, product);
            //product.setId(productSaved.getId());

            ProductRepository.save(product);
        }
    }

}

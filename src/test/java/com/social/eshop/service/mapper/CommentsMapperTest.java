package com.social.eshop.service.mapper;

import com.social.eshop.service.dto.CommentsDTO;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sergey on 26.06.2017.
 */
public class CommentsMapperTest {

    private CommentsMapper sut = new CommentsMapper() {
    };

    @Test
    public void fromId() throws Exception {
        CommentsDTO comments = sut.fromId(1001L);
    }

}
/*     @Autowired
    private CommentsRepository commentsRepository;
//    @Autowired
//    private CustomerRepository customerRepository;

    private CommentsDTOServiceImpl sut = new CommentsDTOServiceImpl(commentsRepository);


    public CommentsDTOServiceImplTest() {
    }


    @Test
    public void findOne() throws Exception {

        CommentsDTO comments = sut.findOne(1001L);
        //assertThat(commentsDTO.getCustomerDTO().getPersonalInformation().getFirstName()).isEqualTo("Vasy");

        if (comments == null) System.out.println("ISKYSTVINII INTELEKT RULIT");//System.out.println(commentsDTO.toString());
    }
//    @Test
//    public void findAll() throws Exception {
//        List<Comments> comments = sut.findAll();
//        if (comments.isEmpty()) System.out.println("ISKYSTVINII INTELEKT RULIT");
//    }

}

*/

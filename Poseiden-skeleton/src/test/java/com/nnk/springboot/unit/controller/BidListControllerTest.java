package com.nnk.springboot.unit.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.List;

public class BidListControllerTest {

    private BidListController bidListController;

    @Mock
    private BidService bidService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bidListController = new BidListController(bidService);
    }

    @Test
    public void testHome() {
        when(bidService.findAllBids()).thenReturn((List<BidList>) Mockito.mock(List.class));
        String viewName = bidListController.home(model);
        assertEquals("bidList/list", viewName);
        verify(bidService, times(1)).findAllBids();
    }

    @Test
    public void testAddBidForm() {
        String viewName = bidListController.addBidForm(Mockito.mock(BidList.class));
        assertEquals("bidList/add", viewName);
    }

    @Test
    public void testValidateWithErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = bidListController.validate(Mockito.mock(BidList.class), bindingResult, model);
        assertEquals("bidList/add", viewName);
    }

    @Test
    public void testValidateWithoutErrors() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = bidListController.validate(Mockito.mock(BidList.class), bindingResult, model);
        assertEquals("redirect:/bidList/list", viewName);
    }   
        
    @Test
    public void testShowUpdateForm() {
        Integer bidId = 1;
        BidList mockBid = Mockito.mock(BidList.class);
        when(bidService.findBidById(bidId)).thenReturn(mockBid);
        Model model = Mockito.mock(Model.class);
        String viewName = bidListController.showUpdateForm(bidId, model);
        assertEquals("bidList/update", viewName);
        verify(bidService, times(1)).findBidById(bidId);
        verify(model, times(1)).addAttribute("bidList", mockBid);
    }
    
    @Test
    public void testUpdateBidWithErrors() {
        Integer bidId = 1;
        BidList mockBid = Mockito.mock(BidList.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        Model model = Mockito.mock(Model.class);
        String viewName = bidListController.updateBid(bidId, mockBid, bindingResult, model);
        assertEquals("bidList/update", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(bidService, never()).updateBid(mockBid); // Make sure updateBid is not called with errors
    }

    @Test
    public void testUpdateBidWithoutErrors() {
        Integer bidId = 1;
        BidList mockBid = Mockito.mock(BidList.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        Model model = Mockito.mock(Model.class);
        String viewName = bidListController.updateBid(bidId, mockBid, bindingResult, model);
        assertEquals("redirect:/bidList/list", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(bidService, times(1)).updateBid(mockBid);
    }

    @Test
    public void testDeleteBid() {
        Integer bidId = 1;
        Model model = Mockito.mock(Model.class);
        String viewName = bidListController.deleteBid(bidId, model);
        assertEquals("redirect:/bidList/list", viewName);
        verify(bidService, times(1)).deleteBidById(bidId);
    }
}

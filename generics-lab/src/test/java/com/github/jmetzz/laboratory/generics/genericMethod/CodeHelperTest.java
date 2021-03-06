package com.github.jmetzz.laboratory.generics.genericMethod;


import com.github.jmetzz.laboratory.generics.genericMethod.pojo.ContractCode;
import com.github.jmetzz.laboratory.generics.genericMethod.pojo.CountryCode;
import com.github.jmetzz.laboratory.generics.genericMethod.pojo.GenderCode;
import org.junit.Test;

import static com.github.jmetzz.laboratory.generics.genericMethod.CodeHelper.APPLICATION_PREDICATE;
import static com.github.jmetzz.laboratory.generics.genericMethod.CodeHelper.CONTRACT_CODE_PREDICATE;
import static com.github.jmetzz.laboratory.generics.genericMethod.CodeHelper.COUNTRY_CODE_PREDICATE;
import static com.github.jmetzz.laboratory.generics.genericMethod.CodeHelper.GENDER_CODE_PREDICATE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CodeHelperTest {


    @Test
    public void shouldCreateContractCode(){
        ContractCode c1 = CodeHelper.getContractCode("007");
        assertThat(c1)
                .isNotNull()
                .matches(CodeHelper.APPLICATION_PREDICATE)
                .matches(CONTRACT_CODE_PREDICATE);

        assertThat(c1.getValue()).isEqualTo("007");
    }

    @Test
    public void shouldCreateCountryCode(){
        CountryCode c1 = CodeHelper.getCountryCode("BR");
        assertThat(c1)
                .isNotNull()
                .matches(APPLICATION_PREDICATE)
                .matches(COUNTRY_CODE_PREDICATE);

        assertThat(c1.getValue()).isEqualTo("BR");
    }


    @Test
    public void shouldCreateGenderCode(){
        GenderCode c1 = CodeHelper.getGenderCode("Male");
        assertThat(c1)
                .isNotNull()
                .matches(APPLICATION_PREDICATE)
                .matches(GENDER_CODE_PREDICATE);

        assertThat(c1.getValue()).isEqualTo("Male");
    }
}
(function($, d) {
  $.ajaxForm = function(options) {
    var form = {
      options: $.extend({
        formSelector: 'form[data-ajax="true"]',
        formRegion: 'main',
        headerSelector: '.site-header',
        errorMessage: "<strong>Error:</strong> We're sorry, but there has been a problem submitting your request. Please confirm all information and try again.",
        successMessage: '<strong>Success:</strong> Your request has been successfully submitted.',
        scrollPadding: 40
      }, options),
      
      $form: function() {
        return $(form.options.formSelector, form.options.formRegion);
      },
      
      $invalid: function() {
        //console.log('getting invalid', $('input:invalid', form.$form()));
        return $('input:invalid', form.$form());
      },

      init: function() {

        var $form = form.$form();
        $('input[required]', $form).each(function(el) {
          form.setFieldWarning(this);
        }).on('keyup blur change', function(el){
          form.validateField(this);
        });
        
        form.toggleSubmitButtons();

        $form.on('click', '[type="submit"]', function(e) {
          e.preventDefault();

          form.toggleSubmitButtons();
          form.submit($(e.target).parents('form'));
          //form.validate($(e.target).parents('form'));
        });
      },

      submit: function(target) {
        var $form = $(target), $alert = $('.alert', $form), successMessage, errorMessage;
        
        successMessage = $alert.data('success-message') !== undefined ? $alert.data('success-message') : form.options.successMessage;
        errorMessage = $alert.data('error-message') !== undefined ? $alert.data('error-message') : form.options.errorMessage;
        
        $alert.addClass('alert-warning').removeClass('alert-success alert-danger hidden').attr('aria-hidden', 'false').attr('role', 'alert').html('<p><span class="glyphicon glyphicon-refresh spin-icon"></span> Submitting the form...</p>');
        
        $.post($form.data('ajax-url'), $form.serialize(), function(result) {}, 'json')
          .done(function() {
            //console.log('form finished');
            $alert.addClass('alert-success').removeClass('alert-warning').html(successMessage);
          })
          .fail(function() {
            //console.log('form failed');
            $alert.addClass('alert-danger').removeClass('alert-warning').html(errorMessage);
            form.toggleSubmitButtons(true);
          });
      },

      validate: function(target) {
        var invalid = $('input:invalid', $(target));

        if (invalid.length < 0) {
          form.submit(target);
        } else {

          invalid.each(function() {
            form.setFieldInvalid(this);
          });

          var HeaderHeight = $(form.options.headerSelector).outerHeight();
          $('html body').scrollTop($(invalid[0]).offset().top - HeaderHeight - form.options.scrollPadding);
          $(invalid[0]).focus();
        }
      },
      
      validateField: function(target) {
        var $target = $(target), $invalid = form.$invalid();
        
        if ($target.is(':valid')) {
          form.setFieldValid(target);
        } else {
          form.setFieldInvalid(target);
        }
        
        //console.log($invalid);
        if ($invalid.length <= 0) {
          //console.log('toggle submit true');
          form.toggleSubmitButtons(true);
        } else {
          form.toggleSubmitButtons();
        }
      },

      setFieldValid: function(target) {
        //console.log('setting field valid', target);
        var $target = $(target),
          $parent = $target.parents('.form-group'),
            $status = $('span.sr-only', $parent),
            $icon = $('span.glyphicon', $parent);
          //$status = $('<span class="sr-only">(success)</span>').attr('id', $target.attr('id') + '-status'),
          //$icon = $('<span class="glyphicon glyphicon-ok form-control-feedback aria-hidden="true"></span>');

        $parent
          .addClass('has-feedback has-success')
        .removeClass('has-error has-warning');
          //.append($icon)
          //.append($status);
        
        $status.html('(success)');
        $icon
          .addClass('glyphicon-ok')
        .removeClass('glyphicon-remove glyphicon-warning-sign');

        $target
          .attr('describedBy', $target.attr('id') + '-status');
      },

      setFieldWarning: function(target) {
        var $target = $(target),
          $parent = $target.parents('.form-group'),
          $status = $('<span class="sr-only">(warning)</span>').attr('id', $target.attr('id') + '-status'),
          $icon = $('<span class="glyphicon glyphicon-warning-sign form-control-feedback aria-hidden="true"></span>');

        $parent
          .addClass('has-feedback has-warning')
          .append($icon)
          .append($status);

        $target
          .attr('describedBy', $target.attr('id') + '-status');
      },

      setFieldInvalid: function(target) {
        var $target = $(target),
          $parent = $target.parents('.form-group'),
            $status = $('span.sr-only', $parent),
            $icon = $('span.glyphicon', $parent);
          //$status = $('<span class="sr-only">(error)</span>').attr('id', $target.attr('id') + '-status'),
          //$icon = $('<span class="glyphicon glyphicon-remove form-control-feedback aria-hidden="true"></span>');

        $parent
          .addClass('has-feedback has-error')
        .removeClass('has-warning has-success');
          //.append($icon)
          //.append($status);

        $status.html('(error)');
        $icon
        .addClass('glyphicon-remove')
        .removeClass('glyphicon-ok glyphicon-warning-sign');
        
        $target
          .attr('describedBy', $target.attr('id') + '-status');
      },
      
      toggleSubmitButtons: function(enable) {
        if (enable === undefined) {
          enable = false;
        }
        
        var $form = form.$form();
        
        if (enable) {
          $('[type="submit"]', form.options.formSelector).removeAttr('disabled');
        } else {
          $('[type="submit"]', form.options.formSelector).attr('disabled', 'disabled');
        }
      }
    };

    return {
      init: form.init()
    };
  };

  $.spamCheck = function(options) {
    var spam = {
      options: $.extend({
        formSelector: 'form[data-spam="true"]',
        isHumanSelector: '#antiSpam_isHuman'
      }, options),

      init: function() {
        $('.js-anti-spam').hide();
        $(d).on('click.antiSpam', spam.isHuman).on("keypress.antiSpam", spam.isHuman);
      },

      isHuman: function() {
        //console.log('should set it true');
        $(spam.options.isHumanSelector).val('true');
        $(d).off('click.antiSpam').off('keypress.antiSpam');
      }
    };

    return {
      init: spam.init()
    };
  };

  $(d).ready(function() {
    var spamCheck = new $.spamCheck(),
      ajaxForm = new $.ajaxForm();
  });
})(jQuery, document);
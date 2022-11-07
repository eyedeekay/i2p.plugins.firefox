/**
 * Mappings
 * Belongs to LocalCDN (since 2020-02-26)
 * (Origin: Decentraleyes)
 *
 * @author      Thomas Rientjes
 * @since       2014-05-30
 *
 * @author      nobody
 * @since       2020-02-26
 *
 * @license     MPL 2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */

'use strict';


/**
 * Mappings
 */

var mappings = {};

/*
 * Date of last update.
 * This only needs to be updated when new domains are added.
 * It's not necessary for subdirectories!
 */
mappings.lastMappingUpdate = '2022-04-18';

mappings.cdn = {

    // Google Hosted Libraries
    'ajax.googleapis.com': {
        '/ajax/libs/': {
            'angularjs/{version}/angular-animate.': resources.angularAnimate,
            'angularjs/{version}/angular-aria.': resources.angularAria,
            'angularjs/{version}/angular-cookies.': resources.angularCookies,
            'angularjs/{version}/angular-loader.': resources.angularLoader,
            'angularjs/{version}/angular-message-format.': resources.angularMessageFormat,
            'angularjs/{version}/angular-messages.': resources.angularMessages,
            'angularjs/{version}/angular-parse-ext.': resources.angularParseExt,
            'angularjs/{version}/angular-resource.': resources.angularResource,
            'angularjs/{version}/angular-route.': resources.angularRoute,
            'angularjs/{version}/angular-sanitize.': resources.angularSanitize,
            'angularjs/{version}/angular-touch.': resources.angularTouch,
            'angularjs/{version}/angular.': resources.angular,
            'angular_material/{version}/angular-material.min.js': resources.angularMaterialDesignJS,
            'angular_material/{version}/angular-material.min.css': resources.angularMaterialDesignCSS,
            'ext-core/{version}/ext-core.': resources.extCore,
            'ext-core/{version}/ext-core-debug.': resources.extCore,
            'jquery/{version}/jquery.': resources.jQuery,
            'jquerymobile/{version}/jquery.mobile.min.js': resources.jQueryMobileJS,
            'jquerymobile/{version}/jquery.mobile.js': resources.jQueryMobileJS,
            'jquerymobile/{version}/jquery.mobile.min.css': resources.jQueryMobileCSS,
            'jquerymobile/{version}/jquery.mobile.css': resources.jQueryMobileCSS,
            'jqueryui/{version}/jquery-ui.js': resources.jQueryUI,
            'jqueryui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'jqueryui/{version}/themes/base/minified/jquery-ui.min.css': resources.jQueryUiThemes,
            'mootools/{version}/mootools-core': resources.mootools,
            'prototype/{version}/prototype.': resources.prototypeJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'webfont/{version}/webfont.': resources.webfontloader,
            'webfont/{version}/webfontloader.js': resources.webfontloader,
            'webfont/1/webfont.js': resources.webfontloader
        }
    },
    // Microsoft Ajax CDN
    'ajax.aspnetcdn.com': {
        '/ajax/': {
            'jQuery/jquery-{version}.': resources.jQuery,
            'jquery/jquery-{version}.': resources.jQuery,
            'jquery.validate/{version}/jquery.validate.': resources.jqueryValidationPlugin,
            'jquery.validation.unobtrusive/{version}/jquery.validate.unobtrusive.': resources.jQueryValidationUnobtrusive,
            'modernizr/modernizr-{version}.': resources.modernizr,
            'bootstrap/{version}/bootstrap.min.': resources.bootstrapJS
        }
    },
    // Microsoft Ajax CDN [Deprecated]
    'ajax.microsoft.com': {
        '/ajax/': {
            'jQuery/jquery-{version}.': resources.jQuery,
            'jquery/jquery-{version}.': resources.jQuery,
            'modernizr/modernizr-{version}.': resources.modernizr
        }
    },
    // CDNJS (Cloudflare)
    'cdnjs.cloudflare.com': {
        '/ajax/libs/': {
            '1000hz-bootstrap-validator/{version}/validator.': resources.bootstrapValidator,
            'ajax-bootstrap-select/{version}/js/ajax-bootstrap-select.': resources.ajaxBootstrapSelect,
            'algoliasearch/{version}/algoliasearch.angular.': resources.algoliaSearch,
            'algoliasearch/{version}/algoliasearch.': resources.algoliaSearch,
            'alpinejs/{version}/alpine.js': resources.alpinejs,
            'anchor-js/{version}/anchor.': resources.anchorJS,
            'angucomplete-alt/{version}/angucomplete-alt.': resources.anguComplete,
            'angular-bootstrap-colorpicker/{version}/css/colorpicker.': resources.angularBootstrapColorpickerCSS,
            'angular-bootstrap-colorpicker/{version}/js/bootstrap-colorpicker-module.': resources.angularBootstrapColorpickerJS,
            'angular-ui-bootstrap/{version}/ui-bootstrap-tpls.': resources.angularUiBootstrapTPLS,
            'angular-ui-bootstrap/{version}/ui-bootstrap.min.js': resources.angularUiBootstrap,
            'angular-ui-router/{version}/angular-ui-router.': resources.angularUiRouter,
            'angular-ui-select/{version}/select.': resources.angularJsUiSelect,
            'angular-ui-utils/{version}/angular-ui-utils.': resources.angularUiUtils,
            'angularjs-slider/{version}/rzslider.': resources.angularJSslider,
            'angular.js/{version}/angular.': resources.angular,
            'angular-animate/{version}/angular-animate': resources.angularAnimate,
            'angular-aria/{version}/angular-aria': resources.angularAria,
            'angular-cookies/{version}/angular-cookies': resources.angularCookies,
            'angular-i18n/{version}/angular-locale': resources.angularI18N,
            'angular-loader/{version}/angular-loader': resources.angularLoader,
            'angular-message-format/{version}/angular-message-format': resources.angularMessageFormat,
            'angular-messages/{version}/angular-messages': resources.angularMessages,
            'angular-parse-ext/{version}/angular-parse-ext': resources.angularParseExt,
            'angular-resource/{version}/angular-resource': resources.angularResource,
            'angular-route/{version}/angular-route': resources.angularRoute,
            'angular-sanitize/{version}/angular-sanitize': resources.angularSanitize,
            'angular-touch/{version}/angular-touch': resources.angularTouch,
            'angular-material/{version}/angular-material.min.css': resources.angularMaterialDesignCSS,
            'angular-material/{version}/angular-material.css': resources.angularMaterialDesignCSS,
            'angular-material/{version}/angular-material.min.js': resources.angularMaterialDesignJS,
            'angular-material/{version}/angular-material.js': resources.angularMaterialDesignJS,
            'angular-translate/{version}/': resources.angularTranslate,
            'angularjs-toaster/{version}/toaster.min.css': resources.angularJsToasterCSS,
            'angularjs-toaster/{version}/toaster.min.js': resources.angularJsToasterJS,
            'angularjs-toaster/{version}/toaster.css': resources.angularJsToasterCSS,
            'angularjs-toaster/{version}/toaster.js': resources.angularJsToasterJS,
            'angular-sanitize/{version}/angular-sanitize.': resources.angularSanitize,
            'animate.css/{version}/animate.': resources.animateCSS,
            'animejs/{version}/anime.': resources.animejs,
            'aos/{version}/aos.css': resources.aosCSS,
            'aos/{version}/aos.js': resources.aosJS,
            'asciinema-player/{version}/asciinema-player.min.js': resources.asciinemaPlayerJS,
            'asciinema-player/{version}/asciinema-player.js': resources.asciinemaPlayerJS,
            'asciinema-player/{version}/asciinema-player.min.css': resources.asciinemaPlayerCSS,
            'asciinema-player/{version}/asciinema-player.css': resources.asciinemaPlayerCSS,
            'axios/{version}/axios.': resources.axios,
            'backbone.js/{version}/backbone.': resources.backbone,
            'backbone.js/{version}/backbone-min.': resources.backbone,
            'baguettebox.js/{version}/baguetteBox.js': resources.baguetteBoxJS,
            'baguettebox.js/{version}/baguetteBox.min.js': resources.baguetteBoxJS,
            'baguettebox.js/{version}/baguetteBox.css': resources.baguetteBoxCSS,
            'baguettebox.js/{version}/baguetteBox.min.css': resources.baguetteBoxCSS,
            'blazy/{version}/blazy.min.js': resources.blazy,
            'bluebird/{version}/bluebird.': resources.bluebird,
            'bodymovin/{version}/bodymovin.js': resources.bodymovin,
            'bodymovin/{version}/bodymovin.min.js': resources.bodymovin,
            'bootbox.js/{version}/bootbox.': resources.bootbox,
            'bootstrap-daterangepicker/{version}/daterangepicker.': resources.bootstrapDaterangepicker,
            'bootstrap-datepicker/{version}/js/bootstrap-datepicker.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/css/bootstrap-datepicker.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/css/bootstrap-datepicker3.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ar.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ar-tn.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.az.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.bg.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.bm.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.bn.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.br.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.bs.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ca.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.cs.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.cy.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.da.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.de.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.el.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.en-AU.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker-en-CA.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.en-CA.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.en-GB.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.en-IE.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.en-NZ.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.en-ZA.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.eo.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.es.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.et.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.eu.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.fa.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.fi.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.fo.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.fr-CH.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.fr.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.gl.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.he.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.hi.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.hr.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.hu.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.hy.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.id.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.is.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.it-CH.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.it.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ja.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ka.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.kh.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.kk.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.km.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ko.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.kr.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.lt.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.lv.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.me.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.mk.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.mn.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ms.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.nl-BE.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.nl.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.no.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.oc.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.pl.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.pt-BR.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.pt.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ro.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.rs-latin.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.rs.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ru.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.si.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.sk.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.sl.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.sq.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.sr-latin.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.sr.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.sv.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.sw.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.ta.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.tg.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.th.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.tk.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.tr.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.uk.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.uz-cyrl.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.uz-latn.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.vi.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.zh-CN.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/locales/bootstrap-datepicker.zh-TW.': resources.bootstrapDatepickerBundle,
            'bootstrap-datepicker/{version}/css/datepicker.': resources.bootstrapDatepickerBundle,
            'bootstrap-icons/{version}/font/bootstrap-icons.css': resources.bootstrapIcons,
            'bootstrap-icons/{version}/font/bootstrap-icons.min.css': resources.bootstrapIcons,
            'bootstrap-multiselect/{version}/js/bootstrap-multiselect.': resources.bootstrapMultiselectJS,
            'bootstrap-select/{version}/css/bootstrap-select.': resources.bootstrapSelectCSS,
            'bootstrap-select/{version}/js/bootstrap-select.': resources.bootstrapSelectJS,
            'bootstrap-slider/{version}/css/bootstrap-slider.min.css': resources.bootstrapSliderCSS,
            'bootstrap-slider/{version}/css/bootstrap-slider.css': resources.bootstrapSliderCSS,
            'bootstrap-slider/{version}/bootstrap-slider.min.js': resources.bootstrapSliderJS,
            'bootstrap-toggle/{version}/js/bootstrap-toggle.': resources.bootstrapToggleJS,
            'bootstrap-toggle/{version}/js/bootstrap2-toggle.': resources.bootstrap2ToggleJS,
            'bootstrap-toggle/{version}/css/bootstrap-toggle.': resources.bootstrapToggleCSS,
            'bootstrap-toggle/{version}/css/bootstrap2-toggle.': resources.bootstrap2ToggleCSS,
            'bootstrap-vue/{version}/bootstrap-vue.min.js': resources.bootstrapVueJS,
            'bootstrap-vue/{version}/bootstrap-vue.js': resources.bootstrapVueJS,
            'bootstrap-vue/{version}/bootstrap-vue.min.css': resources.bootstrapVueCSS,
            'bootstrap-vue/{version}/bootstrap-vue.css': resources.bootstrapVueCSS,
            'bootstrap-3-typeahead/{version}/bootstrap3-typeahead.': resources.bootstrap3Typeahead,
            'bootstrap/{version}/css/bootstrap': resources.bootstrapCSS,
            'bootstrap/{version}/js/bootstrap.': resources.bootstrapJS,
            'bootstrap/{version}/bootstrap.min.js': resources.bootstrapJS,
            'bootstrap/{version}/bootstrap.js': resources.bootstrapJS,
            'bootstrap/{version}/bootstrap.min.css': resources.bootstrapCSS,
            'bootstrap/{version}/bootstrap.css': resources.bootstrapCSS,
            'bowser/{version}/': resources.bowserJS,
            'bower-angular-translate/{version}/angular-translate.': resources.angularTranslate,
            'bower-angular-translate-loader-partial/{version}/angular-translate-loader-partial.': resources.angularTranslateLoaderPartial,
            'bulma/{version}/css/bulma.': resources.bulma,
            'bxslider/{version}/jquery.bxslider.min.js': resources.bxsliderJS,
            'bxslider/{version}/jquery.bxslider.js': resources.bxsliderJS,
            'Chart.js/{version}/Chart.bundle.': resources.chartJs,
            'Chart.js/{version}/Chart.min.js': resources.chartJs,
            'Chart.js/{version}/chart.min.js': resources.chartJs,
            'Chart.js/{version}/Chart.js': resources.chartJs,
            'Chart.js/{version}/chart.js': resources.chartJs,
            'Chart.js/{version}/Chart.css': resources.chartJsCSS,
            'Chart.js/{version}/Chart.min.css': resources.chartJsCSS,
            'chosen/{version}/chosen.jquery.min.js': resources.chosen,
            'clappr/{version}/clappr.': resources.clappr,
            'clipboard.js/{version}/clipboard.min.js': resources.clipboardJS,
            'cookieconsent/{version}/cookieconsent.min.js': resources.cookieconsent2JS,
            'cookieconsent/{version}/cookieconsent.js': resources.cookieconsent2JS,
            'cookieconsent/{version}/cookieconsent.min.css': resources.cookieconsent2CSS,
            'cookieconsent/{version}/cookieconsent.css': resources.cookieconsent2CSS,
            'cookieconsent2/{version}/cookieconsent.min.js': resources.cookieconsent2JS,
            'cookieconsent2/{version}/cookieconsent.js': resources.cookieconsent2JS,
            'cookieconsent2/{version}/cookieconsent.min.css': resources.cookieconsent2CSS,
            'cookieconsent2/{version}/cookieconsent.css': resources.cookieconsent2CSS,
            'corejs-typeahead/{version}/typeahead.bundle.': resources.corejsTypeahead,
            'd3/{version}/d3.min.js': resources.d3JS,
            'd3-legend/{version}/d3-legend.': resources.d3legend,
            'dayjs/{version}/dayjs.': resources.dayjs,
            'dexie/{version}/dexie.': resources.dexie,
            'docsearch.js/{version}/docsearch.min.js': resources.docsearchJS,
            'docsearch.js/{version}/docsearch.js': resources.docsearchJS,
            'docsearch.js/{version}/docsearch.min.css': resources.docsearchCSS,
            'docsearch.js/{version}/docsearch.css': resources.docsearchCSS,
            'docsify/{version}/docsify.min.js': resources.docsify,
            'docsify/{version}/docsify.js': resources.docsify,
            'drawer/{version}/js/drawer.min.js': resources.drawerJS,
            'drawer/{version}/css/drawer.min.css': resources.drawerCSS,
            'dygraph/{version}/dygraph': resources.dygraph,
            'element-ui/{version}/': resources.elementUI,
            'ext-core/{version}/ext-core.': resources.extCore,
            'exif-js/{version}/exif.': resources.exifJS,
            'fancybox/{version}/jquery.fancybox.min.js': resources.fancyBoxJS,
            'fancybox/{version}/jquery.fancybox.js': resources.fancyBoxJS,
            'fancybox/{version}/helpers/jquery.fancybox-media.js': resources.fancyBoxMediaJS,
            'fancybox/{version}/jquery.fancybox.min.css': resources.fancyBoxCSS,
            'fancybox/{version}/jquery.fancybox.css': resources.fancyBoxCSS,
            'fastclick/{version}/fastclick.': resources.fastclick,
            'feather-icons/{version}/feather.': resources.feather,
            'FitText.js/{version}/jquery.fittext.': resources.fitTextJs,
            'flickity/{version}/flickity.pkgd.': resources.flickity,
            'font-awesome/{version}/css/font-awesome': resources.fontawesome,
            'font-awesome/{version}/css/': resources.fontawesome5CSS,
            'font-awesome/{version}/js/': resources.fontawesome5JS,
            'fork-awesome/{version}/css/fork-awesome.': resources.forkawesome,
            'foundation/{version}/css/foundation.min.css': resources.foundationCSS,
            'foundation/{version}/css/foundation.css': resources.foundationCSS,
            'foundation/{version}/js/foundation.min.js': resources.foundationJS,
            'foundation/{version}/js/foundation.js': resources.foundationJS,
            'foundicons/{version}/foundation-icons.min.css': resources.foundationIconsCSS,
            'granim/{version}/granim.': resources.granim,
            'gsap/{version}/': resources.gsapBundle,
            'highlight.js/{version}/': resources.highlightJS,
            'hls.js/{version}/hls.': resources.hlsJS,
            'hogan.js/{version}/hogan.': resources.hoganJS,
            'instantsearch.js/{version}/instantsearch.production.': resources.InstantSearchJS,
            'iScroll/{version}/iscroll.min.js': resources.iScroll,
            'izimodal/{version}/js/iziModal.': resources.izimodal,
            'jasny-bootstrap/{version}/css/jasny-bootstrap.': resources.jasnyBootstrapCSS,
            'jasny-bootstrap/{version}/js/jasny-bootstrap.': resources.jasnyBootstrapJS,
            'jeditable.js/{version}/jquery.jeditable.min.js': resources.jeditableJS,
            'jeditable.js/{version}/jeditable.min.js': resources.jeditableJS,
            'jets/{version}/jets.': resources.jetsJS,
            'jquery/{version}/jquery.': resources.jQuery,
            'jquery/{version}/jquery.min.': resources.jQuery,
            'jquery-ajax-unobtrusive/{version}/jquery.unobtrusive-ajax.': resources.jqueryAjaxUnobtrusive,
            'jquery.devbridge-autocomplete/{version}/jquery.autocomplete.min.js': resources.jQueryAjaxAutoComplete,
            'jquery.blockUI/{version}/jquery.blockUI.min.js': resources.jQueryBlockUI,
            'jquery.colorbox/{version}/jquery.colorbox': resources.jqueryColorbox,
            'jquery-csv/{version}/jquery.csv.min.js': resources.jQueryCSV,
            'jquery.cycle2/{version}/': resources.jqueryCycle2,
            'jquery-easing/{version}/jquery.easing.': resources.jQueryEasing,
            'jquery-jcrop/{version}/css/jquery.Jcrop.': resources.jqueryJcropCSS,
            'jquery-jcrop/{version}/js/jquery.Jcrop.': resources.jqueryJcropJS,
            'jquery.lazyload/{version}/jquery.lazyload.min.js': resources.jQueryLazyLoad,
            'jquery.lazy/{version}/': resources.jQueryLazyBundle,
            'jquery.matchHeight/{version}/jquery.matchHeight-min.js': resources.jqueryMatchHeightJS,
            'jquery-migrate/{version}/jquery-migrate.min.js': resources.jQueryMigrate,
            'jquery-minicolors/{version}/jquery.minicolors.min.js': resources.jqueryMinicolorsJS,
            'jquery-minicolors/{version}/jquery.minicolors.js': resources.jqueryMinicolorsJS,
            'jquery-minicolors/{version}/jquery.minicolors.min.css': resources.jqueryMinicolorsCSS,
            'jquery-minicolors/{version}/jquery.minicolors.css': resources.jqueryMinicolorsCSS,
            'jqueryui/{version}/jquery-ui.js': resources.jQueryUI,
            'jqueryui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'jquery-validate/{version}/jquery.validate.min.js': resources.jqueryValidationPlugin,
            'jquery.tablesorter/{version}/js/jquery.tablesorter.': resources.jQueryTablesorter,
            'jquery-modal/{version}/jquery.modal.js': resources.jQueryModalJS,
            'jquery-modal/{version}/jquery.modal.min.js': resources.jQueryModalJS,
            'jquery-modal/{version}/jquery.modal.css': resources.jQueryModalCSS,
            'jquery-modal/{version}/jquery.modal.min.css': resources.jQueryModalCSS,
            'jquery-mobile/{version}/jquery.mobile.min.js': resources.jQueryMobileJS,
            'jquery-mobile/{version}/jquery.mobile.js': resources.jQueryMobileJS,
            'jquery-mobile/{version}/jquery.mobile.min.css': resources.jQueryMobileCSS,
            'jquery-mobile/{version}/jquery.mobile.css': resources.jQueryMobileCSS,
            'jquery-mousewheel/{version}/jquery.mousewheel.min.js': resources.jqueryMousewheelJS,
            'jquery-cookie/{version}/jquery.cookie.': resources.jqueryCookie,
            'jquery.scrollbar/{version}/jquery.scrollbar.min.js': resources.jQueryScrollbar,
            'jquery.scrollbar/{version}/jquery.scrollbar.js': resources.jQueryScrollbar,
            'jquery.scrollTo/{version}/jquery.scrollTo.': resources.jQueryScrollTo,
            'jquery-timeago/{version}/jquery.timeago.min.js': resources.jqueryTimeago,
            'jquery-timeago/{version}/jquery.timeago.js': resources.jqueryTimeago,
            'jquery-tiny-pubsub/{version}/ba-tiny-pubsub.': resources.jQueryTinyPubsub,
            'jquery-validation-unobtrusive/{version}/jquery.validate.unobtrusive.': resources.jQueryValidationUnobtrusive,
            'jquery-ujs/{version}/rails.': resources.jqueryUJS,
            'jScrollPane/{version}/script/jquery.jscrollpane.': resources.jScrollPane,
            'js-cookie/{version}/js.cookie.min.js': resources.jscookie,
            'knockout/{version}/knockout': resources.knockout,
            'lazysizes/{version}/lazysizes.min.js': resources.lazysizes,
            'leaflet/{version}/leaflet.js': resources.leafletJS,
            'leaflet/{version}/leaflet.css': resources.leafletCSS,
            'leaflet/{version}/leaflet.min.js': resources.leafletJS,
            'leaflet/{version}/leaflet.min.css': resources.leafletCSS,
            'leaflet.markercluster/{version}/leaflet.markercluster': resources.leafletMarkercluster,
            'libphonenumber-js/{version}/libphonenumber-js.': resources.libphonenumber,
            'libsodium-wrappers/{version}/sodium.min.js': resources.libsodiumJS,
            'lightbox2/{version}/js/lightbox.': resources.lightbox2,
            'lightcase/{version}/js/lightcase.': resources.lightcaseJS,
            'lightcase/{version}/css/lightcase.': resources.lightcaseCSS,
            'lightgallery/{version}/js/lightgallery.': resources.lightGalleryJS,
            'lightgallery/{version}/css/lightgallery.': resources.lightGalleryCSS,
            'lodash.js/{version}/lodash.': resources.lodashJS,
            'lozad.js/{version}/lozad.': resources.lozad,
            'lunr.js/{version}/lunr.': resources.lunrJS,
            'magnific-popup.js/{version}/jquery.magnific-popup.min.js': resources.magnificPopupJS,
            'magnific-popup.js/{version}/jquery.magnific-popup.js': resources.magnificPopupJS,
            'magnific-popup.js/{version}/magnific-popup.css': resources.magnificPopupCSS,
            'magnific-popup.js/{version}/magnific-popup.min.css': resources.magnificPopupCSS,
            'markdown-it/{version}/markdown-it.': resources.markdownIt,
            'MaterialDesign-Webfont/{version}/css/materialdesignicons.': resources.materialDesign,
            'materialize/{version}/css/materialize.': resources.materializeCSS,
            'materialize/{version}/js/materialize.': resources.materializeJS,
            'mathjax/{version}/': resources.mathJax,
            'mdbootstrap/{version}/js/mdb.': resources.mdbootstrapJS,
            'mdbootstrap/{version}/css/mdb.': resources.mdbootstrapCSS,
            'mdb-ui-kit/{version}/mdb.js': resources.mdbUiKitJS,
            'mdb-ui-kit/{version}/mdb.min.js': resources.mdbUiKitJS,
            'mdb-ui-kit/{version}/mdb.css': resources.mdbUiKitCSS,
            'mdb-ui-kit/{version}/mdb.min.css': resources.mdbUiKitCSS,
            'Modaal/{version}/js/modaal.': resources.modaalJS,
            'Modaal/{version}/css/modaal.': resources.modaalCSS,
            'modernizr/{version}/modernizr.': resources.modernizr,
            'moment.js/{version}/moment.': resources.moment,
            'moment.js/{version}/moment.min.': resources.moment,
            'moment.js/{version}/moment-with-locales.': resources.moment,
            'mootools/{version}/mootools-core': resources.mootools,
            'noUiSlider/{version}/nouislider.js': resources.noUiSlider,
            'noUiSlider/{version}/nouislider.min.js': resources.noUiSlider,
            'nvd3/{version}/nv.d3.min.js': resources.nvd3JS,
            'nvd3/{version}/nv.d3.js': resources.nvd3JS,
            'nvd3/{version}/nv.d3.min.css': resources.nvd3CSS,
            'nvd3/{version}/nv.d3.css': resources.nvd3CSS,
            'oclazyload/{version}/ocLazyLoad.': resources.ocLazyLoad,
            'OwlCarousel2/{version}/': resources.owlCarousel2Bundle,
            'owl-carousel/{version}/owl.carousel.js': resources.owlCarouselJS,
            'owl-carousel/{version}/owl.carousel.min.js': resources.owlCarouselJS,
            'owl-carousel/{version}/owl.carousel.css': resources.owlCarouselCSScarousel,
            'owl-carousel/{version}/owl.carousel.min.css': resources.owlCarouselCSScarousel,
            'owl-carousel/{version}/owl.theme.': resources.owlCarouselCSStheme,
            'owl-carousel/{version}/owl.transitions.': resources.owlCarouselCSStransitions,
            'p5.js/{version}/p5.': resources.p5JS,
            'p5.js/{version}/addons/p5.sound.': resources.p5JSsound,
            'page.js/{version}/page.min.': resources.pageJs,
            'paginationjs/{version}/pagination.min.js': resources.paginationjsJS,
            'paginationjs/{version}/pagination.js': resources.paginationjsJS,
            'paginationjs/{version}/pagination.min.css': resources.paginationjsCSS,
            'paginationjs/{version}/pagination.css': resources.paginationjsCSS,
            'plyr/{version}/plyr.css': resources.plyrCSS,
            'plyr/{version}/plyr.min.css': resources.plyrCSS,
            'plyr/{version}/plyr.js': resources.plyrJS,
            'plyr/{version}/plyr.min.js': resources.plyrJS,
            'plyr/{version}/plyr.svg': resources.plyrSVG,
            'plyr/{version}/plyr.polyfilled.js': resources.plyrJS,
            'plyr/{version}/plyr.polyfilled.min.js': resources.plyrJS,
            'popper.js/{version}/umd/popper.min.js': resources.popperJS,
            'protonet-jquery.inview/{version}/jquery.inview.': resources.protonetJqueryInview,
            'pure/{version}/': resources.pureCSS,
            'rangeslider.js/{version}/rangeslider.css': resources.rangesliderCSS,
            'rangeslider.js/{version}/rangeslider.min.css': resources.rangesliderCSS,
            'rangeslider.js/{version}/rangeslider.js': resources.rangesliderJS,
            'rangeslider.js/{version}/rangeslider.min.js': resources.rangesliderJS,
            'raven.js/{version}/raven.min.js': resources.ravenJS,
            'raven.js/{version}/plugins/angular.min.js': resources.ravenPluginAngularJS,
            'react/{version}/umd/react.production.min.js': resources.react,
            'react-dom/{version}/umd/react-dom.production.min.js': resources.reactDOM,
            'rickshaw/{version}/rickshaw.min.css': resources.rickshawCSS,
            'rickshaw/{version}/rickshaw.min.js': resources.rickshawJS,
            'script.js/{version}/script.': resources.scriptJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'select2/{version}/': resources.select2,
            'semantic-ui/{version}/': resources.semanticUi,
            'showdown/{version}/showdown.': resources.showdown,
            'showdown/{version}/Showdown.': resources.showdown,
            'simplebar/{version}/simplebar.min.css': resources.simplebarCSS,
            'simplebar/{version}/simplebar.css': resources.simplebarCSS,
            'simplebar/{version}/simplebar.min.js': resources.simplebarJS,
            'simplebar/{version}/simplebar.js': resources.simplebarJS,
            'slick-carousel/{version}/slick.min.css': resources.slickCarouselCSS,
            'slick-carousel/{version}/slick.css': resources.slickCarouselCSS,
            'slick-carousel/{version}/slick.min.js': resources.slickCarouselJS,
            'slick-carousel/{version}/slick.js': resources.slickCarouselJS,
            'slick-carousel/{version}/slick-theme.css': resources.slickCarouselTheme,
            'slick-carousel/{version}/slick-theme.min.css': resources.slickCarouselTheme,
            'slick-lightbox/{version}/slick-lightbox.min.js': resources.slickLightboxJS,
            'slick-lightbox/{version}/slick-lightbox.js': resources.slickLightboxJS,
            'slick-lightbox/{version}/slick-lightbox.css': resources.slickLightboxCSS,
            'slider-pro/{version}/js/jquery.sliderPro.': resources.sliderProJS,
            'spin.js/{version}/spin.min.js': resources.spinJS,
            'sticky-js/{version}/sticky.min.js': resources.stickyJS,
            'stickyfill/{version}/stickyfill.': resources.stickyfill,
            'socket.io/{version}/socket.io.': resources.socketIO,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'Swiper/{version}/css/swiper.': resources.swiperCSS,
            'Swiper/{version}/js/swiper.': resources.swiperJS,
            'Swiper/{version}/swiper-bundle.min.js': resources.swiperJS,
            'Swiper/{version}/swiper-bundle.js': resources.swiperJS,
            'Swiper/{version}/swiper-bundle.min.css': resources.swiperCSS,
            'Swiper/{version}/swiper-bundle.css': resources.swiperCSS,
            'tether/{version}/js/tether.': resources.tetherJS,
            'tinymce/{version}/': resources.tinymce,
            'tooltipster/{version}/js/jquery.tooltipster.': resources.jQueryTooltipster,
            'toastr.js/{version}/toastr.min.css': resources.toastrCSS,
            'toastr.js/{version}/toastr.min.js': resources.toastrJS,
            'toastr.js/{version}/js/toastr.': resources.toastrJS,
            'toastr.js/{version}/css/toastr.': resources.toastrCSS,
            'twitter-bootstrap/{version}/js/bootstrap.min.js': resources.bootstrapJS,
            'twitter-bootstrap/{version}-alpha.3/js/bootstrap.min.js': resources.bootstrapJS,
            'twitter-bootstrap/{version}-alpha.3/css/bootstrap.min.css': resources.bootstrapCSS,
            'twitter-bootstrap/{version}/css/bootstrap': resources.bootstrapCSS,
            'twitter-bootstrap/{version}/js/bootstrap.': resources.bootstrapJS,
            'twitter-bootstrap/{version}/bootstrap.min.js': resources.bootstrapJS,
            'twitter-bootstrap/{version}/bootstrap.js': resources.bootstrapJS,
            'twitter-bootstrap/{version}/bootstrap.min.css': resources.bootstrapCSS,
            'twitter-bootstrap/{version}/bootstrap.css': resources.bootstrapCSS,
            'twix.js/{version}/twix.': resources.twixJS,
            'underscore.js/{version}/underscore.': resources.underscore,
            'underscore.js/{version}/underscore-min.': resources.underscore,
            'urlive/{version}/jquery.urlive.': resources.jqueryURLive,
            'vex-js/{version}/': resources.vexJS,
            'video.js/{version}/video.js': resources.videoJS,
            'vue/{version}/vue.min.js': resources.vueJs,
            'vue-i18n/{version}/vue-i18n.': resources.vueI18N,
            'vue-resource/{version}/vue-resource.': resources.vueResource,
            'vuex/{version}/vuex.min.': resources.vuex,
            'waypoints/{version}/noframework.waypoints.': resources.jQueryWaypoints,
            'waypoints/{version}/jquery.waypoints.': resources.jQueryWaypoints,
            'waypoints/{version}/waypoints.debug.': resources.jQueryWaypoints,
            'waypoints/{version}/zepto.waypoints.': resources.jQueryWaypoints,
            'waypoints/{version}/shortcuts/infinite.': resources.jQueryWaypoints,
            'waypoints/{version}/shortcuts/inview.': resources.jQueryWaypoints,
            'waypoints/{version}/shortcuts/sticky.': resources.jQueryWaypoints,
            'webcomponentsjs/{version}/webcomponents-loader.': resources.webcomponentsJS,
            'webfont/{version}/webfont.js': resources.webfontloader,
            'webfont/{version}/webfontloader.js': resources.webfontloader,
            'webrtc-adapter/{version}/adapter.min.js': resources.webRTCadapter,
            'wow/{version}/wow.min.': resources.wow
        }
    },
    // jQuery CDN (MaxCDN)
    'code.jquery.com': {
        '/': {
            'jquery-{version}.': resources.jQuery,
            'ui/{version}/jquery-ui.': resources.jQueryUI,
            'mobile/{version}/jquery.mobile-{version}.min.js': resources.jQueryMobileJS,
            'mobile/{version}/jquery.mobile-{version}.js': resources.jQueryMobileJS,
            'mobile/{version}/jquery.mobile-{version}.min.css': resources.jQueryMobileCSS,
            'mobile/{version}/jquery.mobile-{version}.css': resources.jQueryMobileCSS,
            'jquery-migrate-{version}.': resources.jQueryMigrate
        }
    },
    // jsDelivr (Cloudflare)
    'cdn.jsdelivr.net': {
        '/npm/': {
            'anchor-js@{version}/anchor.': resources.anchorJS,
            'algoliasearch@{version}/dist/algoliasearch.': resources.algoliaSearch,
            'algoliasearch@{version}/dist/algoliasearch-lite.': resources.algoliaSearch,
            'angular@{version}/angular.': resources.angular,
            'angular@{version}/angular.min.': resources.angular,
            'angular-payments@{version}/lib/angular-payments.js': resources.angularPayments,
            'angular-stripe-checkout@{version}/angular-stripe-checkout.js': resources.angularStripeCheckout,
            'animate.css@{version}/animate.min.css': resources.animateCSS,
            'animejs@{version}/lib/anime.': resources.animejs,
            'aos@{version}/dist/aos.css': resources.aosCSS,
            'aos@{version}/dist/aos.js': resources.aosJS,
            'appboy-web-sdk@{version}/appboy.min.js': resources.appboyWebSdk,
            'autocomplete.js@{version}/dist/autocomplete.': resources.autocompleteJS,
            'axios@{version}/dist/axios.': resources.axios,
            'backbone@{version}/backbone.': resources.backbone,
            'backbone@{version}/backbone-min.': resources.backbone,
            'backbone@{version}/backbone.min.': resources.backbone,
            'bluebird/{version}/bluebird.': resources.bluebird,
            'bluebird@{version}/js/browser/bluebird.': resources.bluebird,
            'bootstrap@{version}/dist/js/bootstrap.': resources.bootstrapJS,
            'bootstrap@{version}/dist/css/bootstrap.': resources.bootstrapCSS,
            'bootstrap-icons@{version}/font/bootstrap-icons.css': resources.bootstrapIcons,
            'bootstrap-icons@{version}/font/bootstrap-icons.min.css': resources.bootstrapIcons,
            'bootstrap-select@{version}/dist/js/bootstrap-select.': resources.bootstrapSelectJS,
            'bootstrap-table@{version}/dist/bootstrap-table.min.js': resources.bootstrapTableJS,
            'bootstrap-table@{version}/dist/bootstrap-table.js': resources.bootstrapTableJS,
            'bootstrap-table@{version}/dist/bootstrap-table.min.css': resources.bootstrapTableCSS,
            'bootstrap-table@{version}/dist/bootstrap-table.css': resources.bootstrapTableCSS,
            'bootstrap-vue@{version}/dist/bootstrap-vue.min.css': resources.bootstrapVueCSS,
            'bootstrap-vue@{version}/dist/bootstrap-vue.css': resources.bootstrapVueCSS,
            'bootstrap-vue@{version}/dist/bootstrap-vue.min.js': resources.bootstrapVueJS,
            'bootstrap-vue@{version}/dist/bootstrap-vue.js': resources.bootstrapVueJS,
            'bowser@{version}/': resources.bowserJS,
            'bulma@{version}/css/bulma.': resources.bulma,
            'bxslider/{version}/jquery.bxslider.min.js': resources.bxsliderJS,
            'bxslider/{version}/jquery.bxslider.js': resources.bxsliderJS,
            'bxslider/{version}/jquery.bxslider.min.css': resources.bxsliderCSS,
            'bxslider/{version}/jquery.bxslider.css': resources.bxsliderCSS,
            'chart.js@{version}': resources.chartJs,
            'clappr@{version}/dist/clappr.': resources.clappr,
            'clipboard@{version}/dist/clipboard.': resources.clipboardJS,
            'cookieconsent@{version}/build/cookieconsent.min.js': resources.cookieconsent2JS,
            'cookieconsent@{version}/build/cookieconsent.js': resources.cookieconsent2JS,
            'cookieconsent@{version}/build/cookieconsent.min.css': resources.cookieconsent2CSS,
            'cookieconsent@{version}/build/cookieconsent.css': resources.cookieconsent2CSS,
            'createjs@{version}/builds/createjs-': resources.createJS,
            'dexie@{version}/dist/dexie.': resources.dexie,
            'docsearch.js@{version}/dist/cdn/docsearch.min.js': resources.docsearchJS,
            'docsearch.js@{version}/dist/cdn/docsearch.js': resources.docsearchJS,
            'docsearch.js@{version}/dist/cdn/docsearch.min.css': resources.docsearchCSS,
            'docsearch.js@{version}/dist/cdn/docsearch.css': resources.docsearchCSS,
            'docsify@{version}/lib/docsify.min.js': resources.docsify,
            'docsify@{version}/lib/docsify.js': resources.docsify,
            'docsify/lib/docsify.min.js': resources.docsify,
            'docsify/lib/docsify.js': resources.docsify,
            'docsify-themeable@{version}/dist/css/theme-defaults.css': resources.docsifyThemeDefault,
            'docsify-themeable@{version}/dist/css/theme-simple.css': resources.docsifyThemeSimple,
            'docsify-themeable@{version}/dist/css/theme-simple-dark.css': resources.docsifyThemeSimpleDark,
            'ethjs@{version}/dist/ethjs.': resources.ethJs,
            'exif-js@{version}/exif.': resources.exifJS,
            '@findify/bundle@{version}/dist/bundle.js': resources.findifyBundle,
            '@findify/bundle@{version}/dist/components.js': resources.findifyBundle,
            '@findify/bundle@{version}/dist/config.js': resources.findifyBundle,
            '@findify/bundle@{version}/dist/initializer.js': resources.findifyBundle,
            '@findify/bundle@{version}/dist/polyfill.js': resources.findifyBundle,
            '@findify/bundle@{version}/dist/vendors~components~initializer.js': resources.findifyBundle,
            '@findify/bundle@{version}/dist/vendors~initializer.js': resources.findifyBundle,
            '@findify/bundle@{version}/dist/vendors~sentry.js': resources.findifyBundle,
            'flv.js/dist/flv.min.js': resources.flvJS,
            'font-awesome@{version}/css/font-awesome.': resources.fontawesome,
            'fork-awesome@{version}/css/fork-awesome.': resources.forkawesome,
            'hls.js/dist/hls.min.js': resources.hlsJS,
            'history@{version}/umd/history.min.js': resources.history,
            'hls.js@{version}': resources.hlsJS,
            'hogan.js@{version}/dist/hogan': resources.hoganJS,
            'instantsearch.js@{version}/dist/instantsearch.production.': resources.InstantSearchJS,
            'instantsearch.js@{version}': resources.InstantSearchJS,
            'instantsearch.css@{version}': resources.InstantSearchCSS,
            'in-view@{version}/dist/in-view.min.js': resources.inView,
            'jquery@{version}/dist/jquery.': resources.jQuery,
            'jquery@{version}/dist/jquery.min.': resources.jQuery,
            'jquery.scrollto@{version}/jquery.scrollTo.': resources.jQueryScrollTo,
            'jquery-ui@{version}/jquery-ui.js': resources.jQueryUI,
            'jquery-ui@{version}/jquery-ui.min.js': resources.jQueryUI,
            'jquery-ui-dist@{version}/jquery-ui.js': resources.jQueryUI,
            'jquery-ui-dist@{version}/jquery-ui.min.js': resources.jQueryUI,
            'jquery-validation@{version}/dist/jquery.validate.': resources.jqueryValidationPlugin,
            'leaflet@{version}/dist/leaflet.js': resources.leafletJS,
            'leaflet@{version}/dist/leaflet.css': resources.leafletCSS,
            'leaflet-easybutton@{version}/src/easy-button.js': resources.leafletEasyButton,
            'leaflet-easybutton@{version}/src/easy-button.min.js': resources.leafletEasyButton,
            'leaflet.featuregroup.subgroup@{version}/dist/leaflet.featuregroup.subgroup.': resources.leafletFeatureGroupSubGroup,
            'lodash@{version}/lodash.min.js': resources.lodashJS,
            'lozad': resources.lozad,
            'lunr@{version}/lunr.': resources.lunrJS,
            'npm-modernizr@{version}/modernizr.': resources.modernizr,
            'npm-modernizr@{version}/modernizr.min.': resources.modernizr,
            'markdown-it@{version}/dist/markdown-it.': resources.markdownIt,
            'mathjax@{version}/es5/': resources.mathJax,
            'moment@{version}/moment.': resources.moment,
            'moment@{version}/moment.min.': resources.moment,
            'mootools@{version}/lib/mootools-core-{version}-server.': resources.mootools,
            'mootools@{version}/lib/mootools-core-{version}-server.min.': resources.mootools,
            'ngx-bootstrap/datepicker/bs-datepicker.css': resources.ngxBootstrapDatepicker,
            'ngx-bootstrap@{version}/datepicker/bs-datepicker.css': resources.ngxBootstrapDatepicker,
            'react-redux@{version}/dist/react-redux.': resources.reactRedux,
            'react-router@{version}/umd/react-router.': resources.reactRouter,
            'react-side-effect@{version}/lib/index.umd.': resources.reactSideEffect,
            'react-lifecycles-compat@{version}/react-lifecycles-compat.': resources.reactLifecyclesCompat,
            'p2p-media-loader-core@{version}/build/p2p-media-loader-core.min.js': resources.p2pMediaLoaderCore,
            'p2p-media-loader-hlsjs@{version}/build/p2p-media-loader-hlsjs.min.js': resources.p2pMediaLoaderHlsJS,
            'p5@{version}/lib/p5.': resources.p5JS,
            'p5@{version}/lib/addons/p5.sound.': resources.p5JSsound,
            'popper.js@{version}/dist/umd/popper.': resources.popperJS,
            '@popperjs/core@{version}/dist/umd/popper.': resources.popperJS,
            'prop-types@{version}/prop-types.': resources.propTypes,
            'purecss@{version}/build/': resources.pureCSS,
            'redux@{version}/dist/redux.': resources.redux,
            'react-dom@{version}/umd/react-dom.production.min.js': resources.reactDOM,
            'react-intl@{version}/react-intl.iife.': resources.reactIntl,
            'react@{version}/umd/react.production.min.js': resources.react,
            'search-insights@{version}/dist/search-insights.': resources.searchInsights,
            'select2@{version}/': resources.select2,
            'swiper@{version}/swiper-bundle.css': resources.swiperCSS,
            'swiper@{version}/swiper-bundle.min.css': resources.swiperCSS,
            'swiper@{version}/swiper-bundle.js': resources.swiperJS,
            'swiper@{version}/swiper-bundle.min.js': resources.swiperJS,
            'swiper/swiper-bundle.css': resources.swiperCSS,
            'swiper/swiper-bundle.min.css': resources.swiperCSS,
            'swiper/swiper-bundle.js': resources.swiperJS,
            'swiper/swiper-bundle.min.js': resources.swiperJS,
            'swfobject@{version}/index.js': resources.swfobject,
            'store-js@{version}/dist/store.legacy.min.js': resources.storeJS,
            'scriptaculous-js@{version}/scriptaculous.': resources.scriptaculous,
            'slick-carousel@{version}/slick/slick.min.css': resources.slickCarouselCSS,
            'slick-carousel@{version}/slick/slick.css': resources.slickCarouselCSS,
            'slick-carousel@{version}/slick/slick.min.js': resources.slickCarouselJS,
            'slick-carousel@{version}/slick/slick.js': resources.slickCarouselJS,
            'underscore@{version}/underscore.': resources.underscore,
            'underscore@{version}/underscore-min.': resources.underscore,
            'urlize.js/urlize.js': resources.urlize,
            'vanilla-lazyload@{version}/dist/lazyload.': resources.vanillaLazyload,
            'videojs-seek-buttons/dist/videojs-seek-buttons.css': resources.videojsSeekButtonsCSS,
            'videojs-seek-buttons/dist/videojs-seek-buttons.min.css': resources.videojsSeekButtonsCSS,
            'videojs-seek-buttons/dist/videojs-seek-buttons.js': resources.videojsSeekButtonsJS,
            'videojs-seek-buttons/dist/videojs-seek-buttons.min.js': resources.videojsSeekButtonsJS,
            'vue-match-media@{version}/dist/index.': resources.vueMatchMedia,
            'vue-resource@{version}/dist/vue-resource.': resources.vueResource,
            'vue@{version}/dist/vue.min.js': resources.vueJs,
            'vue@{version}/dist/vue.js': resources.vueJs,
            'vuex@{version}/dist/vuex.': resources.vuex,
            '@webcomponents/webcomponentsjs/webcomponents-loader.js': resources.webcomponentsJS,
            'webfontloader@{version}/webfontloader.': resources.webfontloader
        },
        '/': {
            'algoliasearch/3/algoliasearch.': resources.algoliaSearch,
            'algoliasearch/{version}/algoliasearchLite.min.js': resources.algoliaSearch,
            'angularjs/{version}/angular.': resources.angular,
            'autocomplete.js/0/autocomplete.': resources.autocompleteJS,
            'backbonejs/{version}/backbone.': resources.backbone,
            'backbonejs/{version}/backbone-min.': resources.backbone,
            'bluebird/{version}/bluebird.': resources.bluebird,
            'bootstrap/{version}/css/bootstrap.': resources.bootstrapCSS,
            'bootstrap/{version}/js/bootstrap.': resources.bootstrapJS,
            'fontawesome/{version}/css/font-awesome.min.css': resources.fontawesome,
            'foundation-icons/{version}/foundation-icons.min.css': resources.foundationIconsCSS,
            'gh/alpinejs/alpine@v{version}/dist/alpine.': resources.alpinejs,
            'gh/fancyapps/fancybox@{version}/dist/jquery.fancybox.js': resources.fancyBoxJS,
            'gh/fancyapps/fancybox@{version}/dist/jquery.fancybox.min.js': resources.fancyBoxJS,
            'gh/fancyapps/fancybox@{version}/dist/jquery.fancybox.css': resources.fancyBoxCSS,
            'gh/fancyapps/fancybox@{version}/dist/jquery.fancybox.min.css': resources.fancyBoxCSS,
            'gh/highlightjs/cdn-release@{version}/build/': resources.highlightJS,
            'gh/johnroy-ui/up@master/materialize.min.css': resources.materializeCSS,
            'gh/johnroy-ui/up@master/materialize.css': resources.materializeCSS,
            'gh/johnroy-ui/up@master/materialize.min.js': resources.materializeJS,
            'gh/johnroy-ui/up@master/materialize.js': resources.materializeJS,
            'gh/johnroy-ui/up@master/jquery-{version}.min.js': resources.jQuery,
            'jquery/{version}/jquery.': resources.jQuery,
            'jquery.ui/{version}/jquery-ui.js': resources.jQueryUI,
            'jquery.ui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'jquery.slick/{version}/slick.css': resources.slickCarouselCSS,
            'jquery.slick/{version}/slick.min.css': resources.slickCarouselCSS,
            'jquery.slick/{version}/slick.js': resources.slickCarouselJS,
            'jquery.slick/{version}/slick.min.js': resources.slickCarouselJS,
            'jquery.validation/{version}/jquery.validate.': resources.jqueryValidationPlugin,
            'momentjs/{version}/moment.': resources.moment,
            'momentjs/{version}/moment.min.': resources.moment,
            'mootools/{version}/mootools-': resources.mootools,
            'simplemde/{version}/simplemde.js': resources.simplemdeJS,
            'simplemde/{version}/simplemde.min.js': resources.simplemdeJS,
            'simplemde/{version}/simplemde.css': resources.simplemdeCSS,
            'simplemde/{version}/simplemde.min.css': resources.simplemdeCSS,
            'snowplow/{version}/sp.': resources.snowplow,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'underscorejs/{version}/underscore.': resources.underscore,
            'underscorejs/{version}/underscore-min.': resources.underscore,
            'webfontloader/{version}/webfont': resources.webfontloader
        },
        // TEMPORARY SOLUTION
        '/g/': {
            'algoliasearch@3(algoliasearchLite.min.js),algoliasearch.helper@2': resources.algoliasearchSearchlightHelper
        }
    },
    // Google Material Icons
    'fonts.googleapis.com': {
        '/': {
            'icon?family=Material+Icons': resources.googleMaterialIcons,
            'css?family=Material+Icons': resources.googleMaterialIcons
        }
    },
    // Yandex CDN
    'yastatic.net': {
        '/': {
            'angularjs/{version}/angular-animate.': resources.angularAnimate,
            'angularjs/{version}/angular-aria.': resources.angularAria,
            'angularjs/{version}/angular-cookies.': resources.angularCookies,
            'angularjs/{version}/angular-loader.': resources.angularLoader,
            'angularjs/{version}/angular-message-format.': resources.angularMessageFormat,
            'angularjs/{version}/angular-messages.': resources.angularMessages,
            'angularjs/{version}/angular-parse-ext.': resources.angularParseExt,
            'angularjs/{version}/angular-resource.': resources.angularResource,
            'angularjs/{version}/angular-route.': resources.angularRoute,
            'angularjs/{version}/angular-sanitize.': resources.angularSanitize,
            'angularjs/{version}/angular-touch.': resources.angularTouch,
            'angularjs/{version}/angular.': resources.angular,
            'backbone/{version}/backbone.': resources.backbone,
            'backbone/{version}/backbone-min.': resources.backbone,
            'ext-core/{version}/ext-core.': resources.extCore,
            'jquery/{version}/jquery.': resources.jQuery,
            'jquery-ui/{version}/jquery-ui.js': resources.jQueryUI,
            'jquery-ui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'modernizr/{version}/modernizr.': resources.modernizr,
            'momentjs/{version}/moment.': resources.moment,
            'momentjs/{version}/moment.min.': resources.moment,
            'prototype/{version}/prototype.': resources.prototypeJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'underscore/{version}/underscore.': resources.underscore,
            'underscore/{version}/underscore-min.': resources.underscore
        }
    },
    // Yandex CDN [Deprecated]
    'yandex.st': {
        '/': {
            'angularjs/{version}/angular.': resources.angular,
            'backbone/{version}/backbone.': resources.backbone,
            'backbone/{version}/backbone-min.': resources.backbone,
            'ext-core/{version}/ext-core.': resources.extCore,
            'highlightjs/{version}/': resources.highlightJS,
            'jquery/{version}/jquery.': resources.jQuery,
            'jquery-ui/{version}/jquery-ui.js': resources.jQueryUI,
            'jquery-ui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'modernizr/{version}/modernizr.': resources.modernizr,
            'momentjs/{version}/moment.': resources.moment,
            'momentjs/{version}/moment.min.': resources.moment,
            'prototype/{version}/prototype.': resources.prototypeJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'underscore/{version}/underscore.': resources.underscore,
            'underscore/{version}/underscore-min.': resources.underscore
        }
    },
    // Baidu CDN
    'apps.bdimg.com': {
        '/libs/': {
            'angular.js/{version}/angular-animate.': resources.angularAnimate,
            'angular.js/{version}/angular-aria.': resources.angularAria,
            'angular.js/{version}/angular-cookies.': resources.angularCookies,
            'angular.js/{version}/angular-loader.': resources.angularLoader,
            'angular.js/{version}/angular-message-format.': resources.angularMessageFormat,
            'angular.js/{version}/angular-messages.': resources.angularMessages,
            'angular.js/{version}/angular-parse-ext.': resources.angularParseExt,
            'angular.js/{version}/angular-resource.': resources.angularResource,
            'angular.js/{version}/angular-route.': resources.angularRoute,
            'angular.js/{version}/angular-sanitize.': resources.angularSanitize,
            'angular.js/{version}/angular-touch.': resources.angularTouch,
            'angular.js/{version}/angular.': resources.angular,
            'backbone.js/{version}/backbone.': resources.backbone,
            'backbone.js/{version}/backbone-min.': resources.backbone,
            'ext-core/{version}/ext-core.': resources.extCore,
            'jquery/{version}/jquery.': resources.jQuery,
            'jqueryui/{version}/jquery-ui.js': resources.jQueryUI,
            'jqueryui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'moment/{version}/moment.': resources.moment,
            'moment/{version}/moment.min.': resources.moment,
            'mootools/{version}/mootools-core.': resources.mootools,
            'prototype/{version}/prototype.': resources.prototypeJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'swfobject/{version}/swfobject_src.': resources.swfobject,
            'underscore.js/{version}/underscore.': resources.underscore,
            'underscore.js/{version}/underscore-min.': resources.underscore,
            'webfont/{version}/webfont.': resources.webfontloader,
            'webfont/{version}/webfont_debug.': resources.webfontloader
        }
    },
    // Baidu CDN
    'libs.baidu.com': {
        '/': {
            'angular.js/{version}/angular-animate.': resources.angularAnimate,
            'angular.js/{version}/angular-aria.': resources.angularAria,
            'angular.js/{version}/angular-cookies.': resources.angularCookies,
            'angular.js/{version}/angular-loader.': resources.angularLoader,
            'angular.js/{version}/angular-message-format.': resources.angularMessageFormat,
            'angular.js/{version}/angular-messages.': resources.angularMessages,
            'angular.js/{version}/angular-parse-ext.': resources.angularParseExt,
            'angular.js/{version}/angular-resource.': resources.angularResource,
            'angular.js/{version}/angular-route.': resources.angularRoute,
            'angular.js/{version}/angular-sanitize.': resources.angularSanitize,
            'angular.js/{version}/angular-touch.': resources.angularTouch,
            'angular.js/{version}/angular.': resources.angular,
            'backbone.js/{version}/backbone.': resources.backbone,
            'backbone.js/{version}/backbone-min.': resources.backbone,
            'ext-core/{version}/ext-core.': resources.extCore,
            'jquery/{version}/jquery.': resources.jQuery,
            'jqueryui/{version}/jquery-ui.js': resources.jQueryUI,
            'jqueryui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'moment/{version}/moment.': resources.moment,
            'moment/{version}/moment.min.': resources.moment,
            'mootools/{version}/mootools-core.': resources.mootools,
            'prototype/{version}/prototype.': resources.prototypeJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'underscore.js/{version}/underscore.': resources.underscore,
            'underscore.js/{version}/underscore-min.': resources.underscore,
            'webfont/{version}/webfont.': resources.webfontloader,
            'webfont/{version}/webfont_debug.': resources.webfontloader
        },
        '/libs/': {
            'angular.js/{version}/angular-animate.': resources.angularAnimate,
            'angular.js/{version}/angular-aria.': resources.angularAria,
            'angular.js/{version}/angular-cookies.': resources.angularCookies,
            'angular.js/{version}/angular-loader.': resources.angularLoader,
            'angular.js/{version}/angular-message-format.': resources.angularMessageFormat,
            'angular.js/{version}/angular-messages.': resources.angularMessages,
            'angular.js/{version}/angular-parse-ext.': resources.angularParseExt,
            'angular.js/{version}/angular-resource.': resources.angularResource,
            'angular.js/{version}/angular-route.': resources.angularRoute,
            'angular.js/{version}/angular-sanitize.': resources.angularSanitize,
            'angular.js/{version}/angular-touch.': resources.angularTouch,
            'angular.js/{version}/angular.': resources.angular,
            'backbone.js/{version}/backbone.': resources.backbone,
            'backbone.js/{version}/backbone-min.': resources.backbone,
            'ext-core/{version}/ext-core.': resources.extCore,
            'jquery/{version}/jquery.': resources.jQuery,
            'jqueryui/{version}/jquery-ui.js': resources.jQueryUI,
            'jqueryui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'mootools/{version}/mootools-core.': resources.mootools,
            'prototype/{version}/prototype.': resources.prototypeJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'underscore.js/{version}/underscore.': resources.underscore,
            'underscore.js/{version}/underscore-min.': resources.underscore,
            'webfont/{version}/webfont.': resources.webfontloader,
            'webfont/{version}/webfont_debug.': resources.webfontloader
        }
    },
    // Staticfile CDN
    'cdn.staticfile.org': {
        '/': {
            'angular.js/{version}/angular-animate.': resources.angularAnimate,
            'angular.js/{version}/angular-aria.': resources.angularAria,
            'angular.js/{version}/angular-cookies.': resources.angularCookies,
            'angular.js/{version}/angular-loader.': resources.angularLoader,
            'angular.js/{version}/angular-message-format.': resources.angularMessageFormat,
            'angular.js/{version}/angular-messages.': resources.angularMessages,
            'angular.js/{version}/angular-parse-ext.': resources.angularParseExt,
            'angular.js/{version}/angular-resource.': resources.angularResource,
            'angular.js/{version}/angular-route.': resources.angularRoute,
            'angular.js/{version}/angular-sanitize.': resources.angularSanitize,
            'angular.js/{version}/angular-touch.': resources.angularTouch,
            'angular.js/{version}/angular.': resources.angular,
            'backbone.js/{version}/backbone.': resources.backbone,
            'backbone.js/{version}/backbone-min.': resources.backbone,
            'ext-core/{version}/ext-core.': resources.extCore,
            'jquery/{version}/jquery.': resources.jQuery,
            'jqueryui/{version}/jquery-ui.js': resources.jQueryUI,
            'jqueryui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'modernizr/{version}/modernizr.': resources.modernizr,
            'mootools/{version}/mootools-core.': resources.mootools,
            'prototype/{version}/prototype.': resources.prototypeJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'webfont/{version}/webfont.': resources.webfontloader,
            'webfont/{version}/webfont_debug.': resources.webfontloader
        }
    },
    // BootCDN
    'cdn.bootcss.com': {
        '/': {
            'angular.js/{version}/angular-animate.': resources.angularAnimate,
            'angular.js/{version}/angular-aria.': resources.angularAria,
            'angular.js/{version}/angular-cookies.': resources.angularCookies,
            'angular.js/{version}/angular-loader.': resources.angularLoader,
            'angular.js/{version}/angular-message-format.': resources.angularMessageFormat,
            'angular.js/{version}/angular-messages.': resources.angularMessages,
            'angular.js/{version}/angular-parse-ext.': resources.angularParseExt,
            'angular.js/{version}/angular-resource.': resources.angularResource,
            'angular.js/{version}/angular-route.': resources.angularRoute,
            'angular.js/{version}/angular-sanitize.': resources.angularSanitize,
            'angular.js/{version}/angular-touch.': resources.angularTouch,
            'angular.js/{version}/angular.': resources.angular,
            'backbone.js/{version}/backbone.': resources.backbone,
            'backbone.js/{version}/backbone-min.': resources.backbone,
            'element-ui/{version}/': resources.elementUI,
            'ext-core/{version}/ext-core.': resources.extCore,
            'font-awesome/{version}/css/font-awesome.': resources.fontawesome,
            'font-awesome/{version}/fonts/': resources.fontawesomeFontsOnly,
            'jquery/{version}/jquery.': resources.jQuery,
            'jqueryui/{version}/jquery-ui.js': resources.jQueryUI,
            'jqueryui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'modernizr/{version}/modernizr.': resources.modernizr,
            'mootools/{version}/mootools-core.': resources.mootools,
            'prototype/{version}/prototype.': resources.prototypeJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'twitter-bootstrap/{version}/js/bootstrap.': resources.bootstrapJS,
            'twitter-bootstrap/{version}/css/bootstrap.': resources.bootstrapCSS,
            'underscore.js/{version}/underscore.': resources.underscore,
            'underscore.js/{version}/underscore-min.': resources.underscore,
            'webfont/{version}/webfont.': resources.webfontloader,
            'webfont/{version}/webfont_debug.': resources.webfontloader,
            'moment.js/{version}/moment.': resources.moment,
            'moment.js/{version}/moment.min.': resources.moment,
            'webfont/{version}/webfontloader.': resources.webfontloader
        }
    },
    // Tencent Public Libraries
    'mat1.gtimg.com': {
        '/libs/': {
            'angular.js/{version}/angular-animate.': resources.angularAnimate,
            'angular.js/{version}/angular-aria.': resources.angularAria,
            'angular.js/{version}/angular-cookies.': resources.angularCookies,
            'angular.js/{version}/angular-loader.': resources.angularLoader,
            'angular.js/{version}/angular-message-format.': resources.angularMessageFormat,
            'angular.js/{version}/angular-messages.': resources.angularMessages,
            'angular.js/{version}/angular-parse-ext.': resources.angularParseExt,
            'angular.js/{version}/angular-resource.': resources.angularResource,
            'angular.js/{version}/angular-route.': resources.angularRoute,
            'angular.js/{version}/angular-sanitize.': resources.angularSanitize,
            'angular.js/{version}/angular-touch.': resources.angularTouch,
            'angular.js/{version}/angular.': resources.angular,
            'jquery/{version}/jquery.': resources.jQuery,
        }
    },
    // Sina Public Resources
    'lib.sinaapp.com': {
        '/js/': {
            'angular.js/angular-{version}/angular.': resources.angular,
            'backbone/{version}/backbone.': resources.backbone,
            'ext-core/{version}/ext-core.': resources.extCore,
            'ext-core/{version}/ext-core-debug.': resources.extCore,
            'jquery/{version}/jquery.': resources.jQuery,
            'jquery/{version}/jquery-': resources.jQuery,
            'jquery-ui/{version}/jquery-ui.js': resources.jQueryUI,
            'jquery-ui/{version}/jquery-ui.min.js': resources.jQueryUI,
            'mootools/{version}/mootools.': resources.mootools,
            'prototype/{version}/prototype.': resources.prototypeJS,
            'scriptaculous/{version}/scriptaculous.': resources.scriptaculous,
            'swfobject/{version}/swfobject.': resources.swfobject,
            'underscore/{version}/underscore.': resources.underscore,
            'underscore/{version}/underscore-min.': resources.underscore,
            'webfont/{version}/webfont.': resources.webfontloader,
            'webfont/{version}/webfont_debug.': resources.webfontloader
        }
    },
    // UpYun Library
    'upcdn.b0.upaiyun.com': {
        '/libs/': {
            'jquery/jquery-{version}.': resources.jQuery,
            'jqueryui/jquery.ui-{version}.js': resources.jQueryUI,
            'jqueryui/jquery.ui-{version}.min.js': resources.jQueryUI,
            'modernizr/modernizr-{version}.': resources.modernizr,
            'mootoolscore/mootools.core-{version}.': resources.mootools
        }
    },
    // StackPath BootstrapCDN
    'stackpath.bootstrapcdn.com': {
        '/': {
            'bootstrap/{version}/js/bootstrap.min.': resources.bootstrapJS,
            'bootstrap/{version}/js/bootstrap.bundle.min.': resources.bootstrapJS,
            'bootstrap/{version}/css/bootstrap.min.': resources.bootstrapCSS,
            'font-awesome/{version}/css/font-awesome': resources.fontawesome
        }
    },
    // MaxCDN Bootstrap
    'maxcdn.bootstrapcdn.com': {
        '/': {
            'bootstrap/{version}/js/bootstrap.bundle.min.': resources.bootstrapJS,
            'bootstrap/{version}/js/bootstrap.min.': resources.bootstrapJS,
            'bootstrap/{version}/css/bootstrap.min.': resources.bootstrapCSS,
            'twitter-bootstrap/{version}/css/bootstrap': resources.bootstrapCSS,
            'bootstrap/{version}/fonts/': resources.bootstrapFontsOnly,
            'bootswatch/{version}/flatly/': resources.bootswatchFlatly,
            'font-awesome/{version}/css/font-awesome': resources.fontawesome,
            'font-awesome/{version}/fonts/': resources.fontawesomeFontsOnly
        }
    },
    // NetDNA Bootstrap
    'netdna.bootstrapcdn.com': {
        '/bootstrap/': {
            '{version}/js/bootstrap.min.': resources.bootstrapJS,
            '{version}/css/bootstrap.min.': resources.bootstrapCSS
        },
        '/font-awesome/': {
            '{version}/css/font-awesome.': resources.fontawesome,
            '{version}/fonts/': resources.fontawesomeFontsOnly
        },
        '/twitter-bootstrap/': {
            '{version}/css/bootstrap.': resources.bootstrapCSS,
            '{version}/css/bootstrap-combined.': resources.bootstrapCSS
        }
    },
    // Font Awesome CDN
    'use.fontawesome.com': {
        '/': {
            'releases/v{version}/css/all': resources.fontawesome5CSS,
            'releases/v{version}/css/brands': resources.fontawesome5CSS,
            'releases/v{version}/css/fontawesome': resources.fontawesome5CSS,
            'releases/v{version}/css/solid': resources.fontawesome5CSS,
            'releases/v{version}/css/v4-shims': resources.fontawesome5CSSv4shims,
            'releases/v{version}/js/': resources.fontawesome5JS,
            'fa-loader.js': resources.webfontloaderFontawesomeJS,
            'fa-loader.css': resources.webfontloaderFontawesomeCSS,
            'webfontloader/{version}/webfontload': resources.webfontloader
        }
    },
    // Cloudflare Rocket-Loader
    'ajax.cloudflare.com': {
        '/': {
            'cdn-cgi/scripts/04b3eb47/cloudflare-static/mirage2.': resources.mirage2
        }
    },
    // Akamai WebCDN
    'akamai-webcdn.kgstatic.net': {
        '/': {
            'renewal/static/js/lozad.min.': resources.lozad
        }
    },
    // gitcdn.github.io
    'gitcdn.github.io': {
        '/': {
            'bootstrap-toggle/{version}/js/bootstrap-toggle.': resources.bootstrapToggleJS,
            'bootstrap-toggle/{version}/js/bootstrap2-toggle.': resources.bootstrap2ToggleJS,
            'bootstrap-toggle/{version}/css/bootstrap-toggle.': resources.bootstrapToggleCSS,
            'bootstrap-toggle/{version}/css/bootstrap2-toggle.': resources.bootstrap2ToggleCSS
        }
    },
    // vjs.zencdn.net
    'vjs.zencdn.net': {
        '/': {
            '{version}/video-js.min.css': resources.videoJScss,
            '{version}/video-js.css': resources.videoJScss,
            '{version}/video.min.js': resources.videoJS,
            '{version}/video.js': resources.videoJS
        }
    },
    // Plyr CDN
    'cdn.plyr.io': {
        '/': {
            '{version}/plyr.svg': resources.plyrSVG
        }
    },
    // MaterialDesign (https://github.com/Templarian/MaterialDesign)
    'cdn.materialdesignicons.com': {
        '/': {
            '{version}/css/materialdesignicons.min.css': resources.materialDesign
        }
    },
    // Sentry's Raven.js (https://codeberg.org/nobody/LocalCDN/issues/214)
    'cdn.ravenjs.com': {
        '/': {
            '{version}/raven.': resources.ravenJS
        }
    },
    // appboy-web-sdk (Braze Web SDK, https://codeberg.org/nobody/LocalCDN/issues/403)
    'js.appboycdn.com': {
        '/': {
            'web-sdk/{version}/appboy.': resources.appboyWebSdk
        }
    },
    // embedly
    'cdn.embed.ly': {
        '/': {
            'player-{version}.min.js': resources.embedlyPlayer
        }
    },
    // datatables
    'cdn.datatables.net': {
        '/': {
            '{version}/': resources.datatables
        }
    },
    // rstudio (MathJax)
    'mathjax.rstudio.com': {
        '/': {
            '{version}': resources.mathJax,
        }
    },
    // MathJax CDN
    'cdn.mathjax.org': {
        '/': {
            'mathjax/{version}/': resources.mathJax,
        }
    },
    // CreateJS
    'code.createjs.com': {
        '/': {
            '{version}/createjs': resources.createJS,
        }
    },
};

// Geekzu Public Service [Mirror]
mappings.cdn['sdn.geekzu.org'] = {
    '/ajax/ajax/libs/': mappings.cdn['ajax.googleapis.com']['/ajax/libs/']
};

// USTC Linux User Group [Mirror]
mappings.cdn['ajax.proxy.ustclug.org'] = mappings.cdn['ajax.googleapis.com'];

// UNPKG (Cloudflare) [Mirror]
mappings.cdn['unpkg.com'] = {
    '/': mappings.cdn['cdn.jsdelivr.net']['/npm/']
};

// PageCDN [Mirror]
mappings.cdn['pagecdn.io'] = {
    '/lib/': mappings.cdn['cdnjs.cloudflare.com']['/ajax/libs/']
};

// loli.net [Mirror]
mappings.cdn['cdnjs.loli.net'] = mappings.cdn['cdnjs.cloudflare.com'];
mappings.cdn['ajax.loli.net'] = mappings.cdn['ajax.googleapis.com'];
mappings.cdn['fonts.loli.net'] = mappings.cdn['fonts.googleapis.com'];

// Qihoo 360 CDN [Mirror]
mappings.cdn['lib.baomitu.com'] = {
    '/': mappings.cdn['cdnjs.cloudflare.com']['/ajax/libs/']
};

// Boot CDN New [Mirror]
mappings.cdn['cdn.bootcdn.net'] = mappings.cdn['cdnjs.cloudflare.com'];

// CDN for the "Block Google Fonts" option (see: https://codeberg.org/nobody/LocalCDN/issues/269)
mappings.cdn['fonts.gstatic.com'] = mappings.cdn['fonts.googleapis.com'];


// Remove Google Fonts Endpoints for Chromium browsers (https://codeberg.org/nobody/LocalCDN/issues/1085)
if (!BrowserType.FIREFOX) {
    delete mappings.cdn['fonts.gstatic.com'];
    delete mappings.cdn['fonts.googleapis.com'];
}


/**
 * List of CNAME CDNs
 * https://codeberg.org/nobody/LocalCDN/issues/816
 *
 */
mappings.cdn['ajax.loli.net.cdn.cloudflare.net'] = mappings.cdn['ajax.loli.net'];
mappings.cdn['akamai-webcdn.kgstatic.net.edgesuite.net'] = mappings.cdn['akamai-webcdn.kgstatic.net'];
mappings.cdn['apps.bdimg.jomodns.com'] = mappings.cdn['apps.bdimg.com'];
mappings.cdn['cdn.bootcdn.net.maoyundns.com'] = mappings.cdn['cdn.bootcdn.net'];
mappings.cdn['cdn.bootcss.com.maoyundns.com'] = mappings.cdn['cdn.bootcss.com'];
mappings.cdn['cdn.embed.ly.cdn.cloudflare.net'] = mappings.cdn['cdn.embed.ly'];
mappings.cdn['cdn.jsdelivr.net.cdn.cloudflare.net'] = mappings.cdn['cdn.jsdelivr.net'];
mappings.cdn['cdnjs.loli.net.cdn.cloudflare.net'] = mappings.cdn['cdnjs.loli.net'];
mappings.cdn['cds.s5x3j6q5.hwcdn.net'] = mappings.cdn['code.jquery.com'];
mappings.cdn['developer.n.shifen.com'] = mappings.cdn['libs.baidu.com'];
mappings.cdn['dualstack.osff.map.fastly.net'] = mappings.cdn['vjs.zencdn.net'];
mappings.cdn['fonts.loli.net.cdn.cloudflare.net'] = mappings.cdn['fonts.loli.net'];
mappings.cdn['gateway.cname.ustclug.org'] = mappings.cdn['ajax.proxy.ustclug.org'];
mappings.cdn['gstaticadssl.l.google.com'] = mappings.cdn['fonts.gstatic.com'];
mappings.cdn['iduwdjf.qiniudns.com'] = mappings.cdn['cdn.staticfile.org'];
mappings.cdn['lb.sae.sina.com.cn'] = mappings.cdn['lib.sinaapp.com'];
mappings.cdn['lib.baomitu.com.qh-cdn.com'] = mappings.cdn['lib.baomitu.com'];
mappings.cdn['mat1.gtimg.com.tegsea.tc.qq.com'] = mappings.cdn['mat1.gtimg.com'];
mappings.cdn['materialdesignicons.b-cdn.net'] = mappings.cdn['cdn.materialdesignicons.com'];
mappings.cdn['mscomajax.vo.msecnd.net'] = mappings.cdn['ajax.aspnetcdn.com'];
mappings.cdn['mscomajax.vo.msecnd.net'] = mappings.cdn['ajax.microsoft.com'];
mappings.cdn['sdn.inbond.gslb.geekzu.org'] = mappings.cdn['sdn.geekzu.org'];
mappings.cdn['use.fontawesome.com.cdn.cloudflare.net'] = mappings.cdn['use.fontawesome.com'];
mappings.cdn['vo.aicdn.com'] = mappings.cdn['upcdn.b0.upaiyun.com'];
